package com.sushanthande.gocorona.ui.covidupdate

import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel

/**
 *Created by Sushant Hande on 07-04-2020
 */
interface CovidUpdateContract {

    interface View {
        fun setCovidUpdate(globalDataModel: GlobalDataModel)

        fun setAllCountryData(countryDataList: List<CountryDataModel>)

        fun onGetDataFailed()
    }

    interface Presenter {
        fun getCovidUpdate()

        fun getAllCountryData()
    }
}