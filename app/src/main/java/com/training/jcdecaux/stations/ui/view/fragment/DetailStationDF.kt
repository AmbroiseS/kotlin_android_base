package com.training.jcdecaux.stations.ui.view.fragment

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.training.jcdecaux.stations.R
import com.training.jcdecaux.stations.StationApp
import com.training.jcdecaux.stations.data.model.Station
import com.training.jcdecaux.stations.ui.presenter.DetailStationPresenter
import com.training.jcdecaux.stations.ui.view.DetailStationView
import javax.inject.Inject

class DetailStationDF : DialogFragment(), DetailStationView {
    lateinit var station: Station

    companion object {
        const val TAG = "DetailsStationDF"
    }

    @Inject
    lateinit var presenter: DetailStationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StationApp.app.inject(this)

        presenter.view = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.df_detail_station, container, false)

        view!!.findViewById<TextView>(R.id.df_name).text = station.name
        view.findViewById<TextView>(R.id.df_avail_bike_stands).text = station.availableBikeStands.toString()
        view.findViewById<TextView>(R.id.df_avail_bikes).text = station.availableBikes.toString()

        view.findViewById<Button>( R.id.df_navigation).setOnClickListener {
            presenter.launchNavigation(station.position!!.lat, station.position!!.lng)
        }

        return view
    }
}