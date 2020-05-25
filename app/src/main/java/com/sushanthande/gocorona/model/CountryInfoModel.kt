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