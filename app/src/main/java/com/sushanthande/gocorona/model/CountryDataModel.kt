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
data class CountryDataModel(
    val country: String,
    val countryInfo: CountryInfoModel?,
    val cases: Long,
    val todayCases: Long,
    val deaths: Long,
    val todayDeaths: Long,
    val recovered: Long,
    val active: Long,
    val critical: Long,
    val casesPerOneMillion: Float?,
    val deathsPerOneMillion: Float?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readParcelable(CountryInfoModel::class.java.classLoader),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeParcelable(countryInfo, flags)
        parcel.writeLong(cases)
        parcel.writeLong(todayCases)
        parcel.writeLong(deaths)
        parcel.writeLong(todayDeaths)
        parcel.writeLong(recovered)
        parcel.writeLong(active)
        parcel.writeLong(critical)
        parcel.writeValue(casesPerOneMillion)
        parcel.writeValue(deathsPerOneMillion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryDataModel> {
        override fun createFromParcel(parcel: Parcel): CountryDataModel {
            return CountryDataModel(parcel)
        }

        override fun newArray(size: Int): Array<CountryDataModel?> {
            return arrayOfNulls(size)
        }
    }
}