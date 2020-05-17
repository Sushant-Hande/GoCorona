package com.sushanthande.gocorona.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by Sushant Hande on 25-03-2020
 */
object ApiClient {
    private const val BASE_API_URL = "https://corona.lmao.ninja/v2/"
    private const val BASE_API_INDIA_URL = "https://api.covid19india.org/"
    private lateinit var globalRetrofitClient: Retrofit
    private lateinit var indiaRetrofitClient: Retrofit
    private val gson = GsonBuilder().create()

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun <T> createGlobalService(serviceClass: Class<T>): T {
        if (!::globalRetrofitClient.isInitialized) {
            globalRetrofitClient = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return globalRetrofitClient.create(serviceClass)
    }

    fun <T> createIndiaService(serviceClass: Class<T>?): T {
        if (!::indiaRetrofitClient.isInitialized) {
            indiaRetrofitClient = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_API_INDIA_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return indiaRetrofitClient.create(serviceClass)
    }
}
