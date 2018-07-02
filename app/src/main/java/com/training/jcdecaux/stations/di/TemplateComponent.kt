package com.training.jcdecaux.stations.di

import android.app.Application
import com.training.jcdecaux.stations.ui.view.activity.MapsActivity
import com.training.jcdecaux.stations.ui.managers.StationRepository
import com.training.jcdecaux.stations.ui.view.fragment.DetailStationDF
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class), (DataModule::class)])
interface TemplateComponent {

    fun inject(application: Application)

    fun inject(mainActivity: MapsActivity)

    fun inject(detailStationDF: DetailStationDF)

    fun getStationManager(): StationRepository
}