package com.sushanthande.gocorona.ui.covidupdate

import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import com.sushanthande.gocorona.network.ApiClient
import com.sushanthande.gocorona.network.ApiRequests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CovidUpdatePresenterImpl(val view: CovidUpdateContract.View) : CovidUpdateContract.Presenter {

    override fun getCovidUpdate() {
        val apiRequest = ApiClient.createGlobalService(ApiRequests.Global::class.java)
        val call = apiRequest?.getGlobalData()
        call?.enqueue(object : Callback<GlobalDataModel> {
            override fun onResponse(
                call: Call<GlobalDataModel>,
                response: Response<GlobalDataModel>
            ) {
                if (response.isSuccessful) {
                    val globalDataModel = response.body()
                    view.setCovidUpdate(globalDataModel)
                }
            }

            override fun onFailure(call: Call<GlobalDataModel>, t: Throwable) {
                view.onGetDataFailed()
            }
        })
    }

    override fun getAllCountryData() {
        val apiRequest = ApiClient.createGlobalService(ApiRequests.Global::class.java)
        val call = apiRequest?.getAllCountryData()
        call?.enqueue(object : Callback<List<CountryDataModel>> {
            override fun onResponse(
                call: Call<List<CountryDataModel>>,
                response: Response<List<CountryDataModel>>
            ) {
                if (response.isSuccessful) {
                    view.setAllCountryData(response.body())
                }
            }

            override fun onFailure(call: Call<List<CountryDataModel>>?, t: Throwable?) {
                view.onGetDataFailed()
            }
        })
    }
}
