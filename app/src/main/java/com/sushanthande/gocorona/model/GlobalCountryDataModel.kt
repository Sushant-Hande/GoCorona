package com.sushanthande.gocorona.model

/**
 *Created by Sushant Hande on 16-05-2020
 */
data class GlobalCountryDataModel(
    val globalDataModel: GlobalDataModel,
    val countryDataList: List<CountryDataModel>
)