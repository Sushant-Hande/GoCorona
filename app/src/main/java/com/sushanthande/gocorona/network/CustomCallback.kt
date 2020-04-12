package com.sushanthande.gocorona.network

import retrofit2.Response

/**
 *Created by Sushant Hande on 12-04-2020
 */
interface CustomCallback {
    fun onSuccess(response: Response<ApiResponse>)
    fun onFailed(error: String)
}