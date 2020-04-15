package com.sushanthande.gocorona.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.ui.dashboard.DashboardActivity

/**
 *Created by Sushant Hande on 25-03-2020
 */
class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }, 3000)
    }
}
