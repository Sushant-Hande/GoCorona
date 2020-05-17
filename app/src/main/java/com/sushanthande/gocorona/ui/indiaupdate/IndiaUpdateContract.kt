package com.sushanthande.gocorona.ui.indiaupdate

import com.sushanthande.gocorona.model.StateModel

/**
 *Created by Sushant Hande on 11-04-2020
 */
interface IndiaUpdateContract {

    interface View {
        fun showParentView()
        fun hideParentView()
        fun showProgressBar()
        fun hideProgressBar()
        fun showCheckInternetView()
        fun onRetryClick()
        fun setIndiaUpdate(stateModelList: ArrayList<StateModel>)
    }

    interface Presenter {
        fun getIndiaUpdate()
        fun onRetryClick()
        fun clear()
    }
}