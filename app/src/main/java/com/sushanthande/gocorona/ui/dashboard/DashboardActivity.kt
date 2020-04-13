package com.sushanthande.gocorona.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.sushanthande.gocorona.BuildConfig
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.R.layout.dashboard_activity
import com.sushanthande.gocorona.adapter.ViewPagerAdapter
import com.sushanthande.gocorona.ui.dashboard.fragments.homefragment.HomeFragment
import com.sushanthande.gocorona.ui.dashboard.fragments.setting.SettingFragment
import kotlinx.android.synthetic.main.dashboard_activity.*

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardActivity : AppCompatActivity(),
    DashboardContract.View {
    private lateinit var presenter: DashboardContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dashboard_activity)
        setSupportActionBar(toolBar)
        presenter = DashboardPresenterImpl(this)
        presenter.setScreenDetails()
    }

    override fun setScreenDetails() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), getString(R.string.home))
        adapter.addFragment(SettingFragment(), getString(R.string.setting))
        val limit =
            if (adapter.count > 1) adapter.count - 1 else 1
        viewPager.offscreenPageLimit = limit
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        //load add
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                print("Add Loaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                print("Add Failed To Load")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
                    var shareMessage = "\n${R.string.share_app}\n\n"
                    shareMessage =
                        """
                    ${shareMessage}Shared by https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                    """.trimIndent()
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
