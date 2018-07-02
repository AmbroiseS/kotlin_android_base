package com.training.jcdecaux.stations.rest.dtos

data class StationDto(val number: String, val name: String, val adresse: String, val position: PositionDto, val available_bike_stands: Int, val available_bikes: Int)