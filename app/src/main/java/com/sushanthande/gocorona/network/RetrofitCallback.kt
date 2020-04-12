package com.sushanthande.gocorona.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *Created by Sushant Hande on 11-04-2020
 */
open class RetrofitCallback(private val customCallback: CustomCallback) : Callback<ApiResponse> {

    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
        if (response.isSuccessful) {
            customCallback.onSuccess(response)
        } else {
            customCallback.onFailed("")
        }
    }

    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
        customCallback.onFailed("")
    }
}
