package com.sushanthande.gocorona.ui.covidupdate

import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel

/**
 *Created by Sushant Hande on 07-04-2020
 */
interface CovidUpdateContract {

    interface View {
        fun showParentView()
        fun hideParentView()
        fun showProgressBar()
        fun hideProgressBar()
        fun showCheckInternetView()
        fun onRetryClick()
        fun setCovidUpdate(globalDataModel: GlobalDataModel)
        fun setAllCountryData(countryDataList: List<CountryDataModel>)
    }

    interface Presenter {
        fun getGlobalAndAllCountryUpdates()
        fun clear()
    }
}