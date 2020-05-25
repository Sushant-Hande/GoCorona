package com.sushanthande.gocorona.network

import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *Created by Sushant Hande on 25-03-2020
 */
interface ApiRequests {

    interface Global {
        @GET("all")
        fun getGlobalData(): Observable<GlobalDataModel>

        @GET("countries?sort=country")
        fun getAllCountryData(): Observable<List<CountryDataModel>>
    }

    interface India {
        @GET("data.json")
        fun getData():Observable<ApiResponse>
    }

}