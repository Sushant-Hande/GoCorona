package com.sushanthande.gocorona.model

import android.provider.Settings

/**
 *Created by Sushant Hande on 16-05-2020
 */
data class GlobalCountryDataModel(
    val globalDataModel: GlobalDataModel,
    val countryDataList: List<CountryDataModel>
)