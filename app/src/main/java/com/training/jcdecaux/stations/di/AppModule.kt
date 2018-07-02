package com.training.jcdecaux.stations.di

import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.training.jcdecaux.stations.StationApp.Companion.app
import com.training.jcdecaux.stations.di.qualifier.ApplicationQualifier
import com.training.jcdecaux.stations.ui.presenter.MapsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationQualifier
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideLocationManager(): LocationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

}