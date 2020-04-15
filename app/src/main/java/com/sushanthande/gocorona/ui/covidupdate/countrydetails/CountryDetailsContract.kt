package com.sushanthande.gocorona.ui.covidupdate.countrydetails

import com.sushanthande.gocorona.model.CountryDataModel

/**
 *Created by Sushant Hande on 07-04-2020
 */
interface CountryDetailsContract {

    interface View {
        fun setCountryDetails(countryDataModel: CountryDataModel)
    }

    interface Presenter {
        fun setCountryDetails(countryDataModel: CountryDataModel)
    }
}