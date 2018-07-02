package com.training.jcdecaux.stations.rest.clients

import com.training.jcdecaux.stations.rest.dtos.StationDto
import io.reactivex.Observable
import retrofit2.http.GET

interface JCDRestClient {

    @GET("stations")
    fun getStations(): Observable<List<StationDto>>
}