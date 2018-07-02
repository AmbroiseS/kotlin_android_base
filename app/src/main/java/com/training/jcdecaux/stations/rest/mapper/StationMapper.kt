package com.training.jcdecaux.stations.rest.mapper

import com.training.jcdecaux.stations.data.model.Station
import com.training.jcdecaux.stations.rest.dtos.StationDto
import javax.inject.Inject

class StationMapper @Inject constructor(private var positionMapper: PositionMapper) {

    fun mapDTOtoStation(dtos: List<StationDto>): List<Station> = dtos.map { mapDTOtoStation(it) }


    private fun mapDTOtoStation(dto: StationDto): Station = dto.number.let {
        Station(dto.number, dto.name, dto.adresse,
                positionMapper.mapDTOtoPosition(dto.position),
                dto.available_bike_stands, dto.available_bikes)
    }

}
