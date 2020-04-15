package com.sushanthande.gocorona.ui.dashboard.fragments.homefragment

import com.sushanthande.gocorona.model.DashboardMenu

/**
 *Created by Sushant Hande on 25-03-2020
 */
class HomeFragmentPresenterImpl(val view: HomeFragmentContract.View) :
    HomeFragmentContract.Presenter {

    override fun setDashboardMenu(menuList: ArrayList<DashboardMenu>) {
        view.setDashboardMenu(menuList)
    }

}
