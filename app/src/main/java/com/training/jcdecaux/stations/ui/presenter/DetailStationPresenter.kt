package com.training.jcdecaux.stations.ui.presenter

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import com.training.jcdecaux.stations.ui.view.DetailStationView
import javax.inject.Inject


class DetailStationPresenter @Inject constructor(private val application: Application) : MVPPresenter<DetailStationView>() {

    fun launchNavigation(lat: Double, lng : Double) {
        val location = lat.toString() + "," + lng
        val gmmIntentUri = Uri.parse("google.navigation:q=$location")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(application, mapIntent, null)
    }
}