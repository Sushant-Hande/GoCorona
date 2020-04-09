package com.sushanthande.gocorona.model

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardMenu {

    var id: Int = 0

    var name: String? = null

    var image: Drawable? = null

    var tintColor: Int = 0

    var backgroundTint: ColorStateList? = null

    enum class Menu(val id: Int) {
        CORONA_UPDATES(1),
        WHAT_IS_CORONA(2),
        SYMPTOMS(3),
        PRECAUTIONS(4)
    }
}