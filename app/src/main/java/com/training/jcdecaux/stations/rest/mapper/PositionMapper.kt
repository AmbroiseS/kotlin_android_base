package com.training.jcdecaux.stations.rest.mapper

import com.training.jcdecaux.stations.data.model.Position
import com.training.jcdecaux.stations.rest.dtos.PositionDto
import javax.inject.Inject

class PositionMapper @Inject constructor() {

    fun mapDTOtoPosition(dto: PositionDto): Position =
            Position(dto.lat, dto.lng)

}