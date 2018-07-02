package com.training.jcdecaux.stations.ui.view

import android.location.Location
import com.training.jcdecaux.stations.data.model.Station

interface MapsView : MVPView {

    fun getPosition(): Location?

    fun displayStationsOnMap(list: List<Station>)
}