package com.training.jcdecaux.stations.ui.view.activity

import android.Manifest
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.training.jcdecaux.stations.R
import com.training.jcdecaux.stations.StationApp
import com.training.jcdecaux.stations.data.model.Station
import com.training.jcdecaux.stations.ui.presenter.MVPPresenter
import com.training.jcdecaux.stations.ui.presenter.MapsPresenter
import com.training.jcdecaux.stations.ui.view.MapsView
import com.training.jcdecaux.stations.ui.view.fragment.DetailStationDF
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import javax.inject.Inject

@RuntimePermissions
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsView {
    private var location: Location? = null
    private var hasLocation = false
    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var locationManager: LocationManager

    @Inject
    lateinit var presenter: MapsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        StationApp.app.inject(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        presenter.view = this
        presenter.init()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(-34.0, 151.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10F))

        mMap.setOnInfoWindowClickListener {
            val df = DetailStationDF()
            val fm = this@MapsActivity.fragmentManager
            df.station= it.tag as Station
            df.show(fm, DetailStationDF.TAG)
        }

        tryLocWithPermissionCheck()

    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun tryLoc() {
        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                this@MapsActivity.location = location
                presenter.init()
                if (!hasLocation) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(location!!.latitude, location.longitude)))
                    hasLocation = true
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }

        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 100F, locationListener)
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onCameraDenied() {
        Toast.makeText(this, "Please allow permissions to use the app", Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onCameraNeverAskAgain() {
        Toast.makeText(this, "Please allow permissions to use the app", Toast.LENGTH_SHORT).show()
    }


    override fun getPosition(): Location? = location

    override fun displayStationsOnMap(list: List<Station>) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(LatLng(location!!.latitude, location!!.longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        list.forEach { mMap.addMarker(MarkerOptions().position(LatLng(it.position!!.lat, it.position.lng)).title(it.name)).tag=it }

    }

}
