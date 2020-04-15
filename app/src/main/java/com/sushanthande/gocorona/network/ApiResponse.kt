package com.sushanthande.gocorona.network

import com.google.gson.annotations.SerializedName
import com.sushanthande.gocorona.model.StateModel

/**
 *Created by Sushant Hande on 11-04-2020
 */
data class ApiResponse(

    @SerializedName("statewise")
    val stateList:List<StateModel>
)