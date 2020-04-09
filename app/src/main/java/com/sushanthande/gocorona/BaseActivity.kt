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
