package com.sushanthande.gocorona.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.ViewPagerAdapter
import com.sushanthande.gocorona.databinding.DashboardActivityBinding
import com.sushanthande.gocorona.ui.dashboard.fragments.homefragment.HomeFragment
import com.sushanthande.gocorona.ui.dashboard.fragments.setting.SettingFragment

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardActivity : AppCompatActivity(),
    DashboardContract.View {

    private lateinit var binding: DashboardActivityBinding
    private lateinit var presenter: DashboardContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard_activity)
        setSupportActionBar(binding.toolBar)
        presenter = DashboardPresenterImpl(this)
        presenter.setScreenDetails()
    }

    override fun setScreenDetails() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), getString(R.string.home))
        adapter.addFragment(SettingFragment(), getString(R.string.setting))
        val limit =
            if (adapter.count > 1) adapter.count - 1 else 1
        binding.viewPager.offscreenPageLimit = limit
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
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
                    val shareMessage = "\n${getString(R.string.share_app)}\n"
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
