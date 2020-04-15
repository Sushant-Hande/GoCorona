package com.sushanthande.gocorona.ui.dashboard.fragments.homefragment

import com.sushanthande.gocorona.model.DashboardMenu

/**
 *Created by Sushant Hande on 25-03-2020
 */
interface HomeFragmentContract {

    interface View {
        fun setDashboardMenu(menuList:ArrayList<DashboardMenu>)
    }

    interface Presenter {
        fun setDashboardMenu(menuList:ArrayList<DashboardMenu>)
    }

}