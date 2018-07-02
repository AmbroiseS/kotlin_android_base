package com.training.jcdecaux.stations.ui.managers

import com.training.jcdecaux.stations.data.model.Station
import com.training.jcdecaux.stations.rest.mapper.StationMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class StationRepository @Inject constructor(private val stationLoader: StationLoader, private val stationMapper: StationMapper) {

    fun fetchStations(onResponse: (List<Station>) -> Unit) {
        stationLoader.fetchStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onResponse.invoke(stationMapper.mapDTOtoStation(it))
                }
    }

}