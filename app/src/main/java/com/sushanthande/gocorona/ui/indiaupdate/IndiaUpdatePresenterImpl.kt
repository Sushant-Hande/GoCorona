package com.sushanthande.gocorona.ui.indiaupdate

import com.sushanthande.gocorona.model.StateModel
import com.sushanthande.gocorona.network.*
import retrofit2.Response

/**
 *Created by Sushant Hande on 11-04-2020
 */
class IndiaUpdatePresenterImpl(val view: IndiaUpdateContract.View) : IndiaUpdateContract.Presenter {

    override fun getIndiaUpdate() {
        view.hideParentView()
        view.showShimmerView()
        val apiRequest = ApiClient.createIndiaService(ApiRequests.India::class.java)
        val call = apiRequest?.getData()
        call?.enqueue(RetrofitCallback(object : CustomCallback {
            override fun onSuccess(response: Response<ApiResponse>) {
                view.setIndiaUpdate(response.body().stateList as ArrayList<StateModel>)
            }

            override fun onFailed(error: String) {
                view.hideShimmerView()
                view.hideParentView()
                view.showCheckInternetView()
            }
        }))
    }

    override fun onRetryClick() {
        view.onRetryClick()
    }
}