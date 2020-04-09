package com.sushanthande.gocorona.ui.dashboard

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardPresenterImpl(val view: DashboardContract.View) :
    DashboardContract.Presenter {

    override fun setScreenDetails() {
        view.setScreenDetails()
    }
}