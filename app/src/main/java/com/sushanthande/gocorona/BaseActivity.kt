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

package com.sushanthande.gocorona

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pixplicity.easyprefs.library.Prefs
import com.sushanthande.gocorona.ui.dashboard.fragments.setting.SettingFragment
import java.util.*

/**
 *Created by Sushant Hande on 25-03-2020
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefs.getString(SettingFragment.SELECTED_LANGUAGE, null)
            ?.let {
                val myLocale = Locale(it)
                val res = resources
                val dm = res.displayMetrics
                val conf = res.configuration
                conf.setLocale(myLocale)
                res.updateConfiguration(conf, dm)
            }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }
}
