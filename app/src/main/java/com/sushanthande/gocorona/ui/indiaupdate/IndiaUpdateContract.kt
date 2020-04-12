package com.sushanthande.gocorona.ui.indiaupdate

import com.sushanthande.gocorona.model.StateModel

/**
 *Created by Sushant Hande on 11-04-2020
 */
interface IndiaUpdateContract {

    interface View {
        fun setIndiaUpdate(stateModelList: ArrayList<StateModel>)
        fun showParentView()
        fun hideParentView()
        fun showShimmerView()
        fun hideShimmerView()
        fun showCheckInternetView()
        fun onRetryClick()
    }

    interface Presenter {
        fun getIndiaUpdate()
        fun onRetryClick()
    }
}