/*
 * Copyright 2020 Sushant Hande
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sushanthande.gocorona.model

import android.os.Parcel
import android.os.Parcelable

/**
 *Created by Sushant Hande on 25-03-2020
 */
data class CountryInfoModel(
    val _id: Int,
    val lat: Float,
    val long: Float,
    val flag: String?,
    val iso3: String?,
    val iso2: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(_id)
        parcel.writeFloat(lat)
        parcel.writeFloat(long)
        parcel.writeString(flag)
        parcel.writeString(iso3)
        parcel.writeString(iso2)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryInfoModel> {
        override fun createFromParcel(parcel: Parcel): CountryInfoModel {
            return CountryInfoModel(parcel)
        }

        override fun newArray(size: Int): Array<CountryInfoModel?> {
            return arrayOfNulls(size)
        }
    }
}