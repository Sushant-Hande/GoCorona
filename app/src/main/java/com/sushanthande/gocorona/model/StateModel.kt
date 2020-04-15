package com.sushanthande.gocorona.model

import com.google.gson.annotations.SerializedName

/**
 *Created by Sushant Hande on 11-04-2020
 */
data class StateModel(
    val active: Long,
    val confirmed: Long,
    val deaths: Long,
    @SerializedName("deltaconfirmed")
    val deltaConfirmed: Long,
    @SerializedName("deltadeaths")
    val deltaDeaths: Long,
    @SerializedName("deltarecovered")
    val deltaRecovered: Long,
    @SerializedName("lastupdatedtime")
    val lastUpdatedTime: String,
    val recovered: Long,
    val state: String,
    @SerializedName("statecode")
    val stateCode: String
)
