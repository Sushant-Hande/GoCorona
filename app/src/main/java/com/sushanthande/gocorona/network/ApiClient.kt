package com.sushanthande.gocorona.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by Sushant Hande on 25-03-2020
 */
object ApiClient {
    private val BASE_API_URL = "https://corona.lmao.ninja/"
    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().create()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun <T> createService(serviceClass: Class<T>?): T? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit?.create(serviceClass)
    }
}
