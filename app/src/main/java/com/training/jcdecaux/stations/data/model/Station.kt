package com.training.jcdecaux.stations.data.model

data class Station(
        val id: String?,
        val name: String?,
        val adresse: String?,
        val position: Position?,
        val availableBikeStands: Int?,
        val availableBikes: Int?)