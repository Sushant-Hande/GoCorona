package com.sushanthande.gocorona.network

import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import com.sushanthande.gocorona.model.StateModel
import retrofit2.Call
import retrofit2.http.GET

/**
 *Created by Sushant Hande on 25-03-2020
 */
interface ApiRequests {

    interface Global {
        @GET("all")
        fun getGlobalData(): Call<GlobalDataModel>

        @GET("countries?sort=country")
        fun getAllCountryData(): Call<List<CountryDataModel>>
    }

    interface India {
        @GET("data.json")
        fun getData():Call<ApiResponse>
    }

}