package com.training.jcdecaux.stations.ui.managers

import com.training.jcdecaux.stations.rest.clients.JCDRestClient
import com.training.jcdecaux.stations.rest.dtos.StationDto
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class StationLoader @Inject constructor(private val restClient: JCDRestClient) {

    fun fetchStations(): Observable<List<StationDto>> {
        return restClient.getStations()
    }

}