package com.training.jcdecaux.stations.ui.presenter

import com.training.jcdecaux.stations.ui.managers.StationRepository
import com.training.jcdecaux.stations.ui.utils.LocationUtils
import com.training.jcdecaux.stations.ui.view.MapsView
import javax.inject.Inject

class MapsPresenter @Inject constructor(private val stationRepository: StationRepository, private val locationUtils: LocationUtils) : MVPPresenter<MapsView>() {
    private val DISTANCE_FROM_USER: Int = 50_000

    override fun init() {
        getStations(DISTANCE_FROM_USER)
    }

    fun getStations(distanceFromUser: Int) {
        stationRepository.fetchStations {

            if (view.getPosition() != null) {
                val listStationCloseToUser = locationUtils.getStationsCloseToUser(it, view.getPosition()!!, distanceFromUser)

                view.displayStationsOnMap(listStationCloseToUser)
            }
        }
    }
}