package com.sushanthande.gocorona

import android.app.Application
import android.content.ContextWrapper
import com.pixplicity.easyprefs.library.Prefs

/**
 *Created by Sushant Hande on 25-03-2020
 */
class GCApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
    }
}
