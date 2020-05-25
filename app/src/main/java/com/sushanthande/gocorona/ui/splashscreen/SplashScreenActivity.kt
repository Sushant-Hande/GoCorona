package com.sushanthande.gocorona.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.pixplicity.easyprefs.library.Prefs
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.ui.dashboard.DashboardActivity
import com.sushanthande.gocorona.ui.onboarding.OnBoardingActivity

/**
 *Created by Sushant Hande on 25-03-2020
 */
class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            if (Prefs.getBoolean(OnBoardingActivity.IS_ON_BOARDING_SCREENS_VISITED, false)) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                startActivity(Intent(this, OnBoardingActivity::class.java))
            }
            finish()
        }, 3000)
    }
}
