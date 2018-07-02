package com.training.jcdecaux.stations.ui.utils

import android.location.Location
import com.training.jcdecaux.stations.data.model.Station
import javax.inject.Inject

class LocationUtils @Inject constructor() {

    fun getStationsCloseToUser(list: List<Station>, location: Location, distanceInMeter: Int): List<Station> =
            list.filter { isStationCloseToUser(it, location, distanceInMeter) }


    private fun isStationCloseToUser(station: Station, userLocation: Location, distanceInMeter: Int): Boolean {
        val location = Location("")
        location.latitude = station.position!!.lat
        location.longitude = station.position.lng
        return userLocation.distanceTo(location) < distanceInMeter
    }

}