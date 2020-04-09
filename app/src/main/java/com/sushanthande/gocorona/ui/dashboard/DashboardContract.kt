package com.sushanthande.gocorona.ui.dashboard

/**
 *Created by Sushant Hande on 25-03-2020
 */
interface DashboardContract {

    interface View {
        fun setScreenDetails()
    }

    interface Presenter {
        fun setScreenDetails()
    }

}