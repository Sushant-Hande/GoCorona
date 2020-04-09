package com.sushanthande.gocorona.ui.covidupdate.countrydetails

import com.sushanthande.gocorona.model.CountryDataModel

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CountryDetailsPresenterImpl(val view: CountryDetailsContract.View) :
    CountryDetailsContract.Presenter {

    override fun setCountryDetails(countryDataModel: CountryDataModel) {
        view.setCountryDetails(countryDataModel)
    }
}