package com.training.jcdecaux.stations

import android.app.Application
import com.training.jcdecaux.stations.di.*
import javax.inject.Inject

class StationApp : Application() {

    companion object {
        @JvmStatic
        lateinit var app: TemplateComponent
    }

    @Inject
    lateinit var dataModule: DataModule

    override fun onCreate() {
        super.onCreate()
        app = DaggerTemplateComponent.builder().appModule(AppModule(this)).build()
    }


}