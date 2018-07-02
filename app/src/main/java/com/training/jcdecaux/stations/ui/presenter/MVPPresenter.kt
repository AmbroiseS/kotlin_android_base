package com.training.jcdecaux.stations.ui.presenter

import com.training.jcdecaux.stations.ui.view.MVPView

abstract class MVPPresenter<T> where T : MVPView {
    lateinit var view: T

    open fun init() {}

}