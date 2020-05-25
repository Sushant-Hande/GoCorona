package com.sushanthande.gocorona.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.pixplicity.easyprefs.library.Prefs
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.OnBoardingAdapter
import com.sushanthande.gocorona.databinding.OnboardingActivityBinding
import com.sushanthande.gocorona.model.OnBoardingModel
import com.sushanthande.gocorona.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.onboarding_activity.*


/**
 *Created by Sushant Hande on 18-05-2020
 */
class OnBoardingActivity : AppCompatActivity(), OnBoardingContract.View {

    lateinit var binding: OnboardingActivityBinding
    lateinit var presenter: OnBoardingContract.Presenter
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.onboarding_activity)
        presenter = OnBoardingPresenterImpl(this)
        presenter.setOnBoardingData(getOnBoardingList())
        binding.onBoardingBtn.setOnClickListener {
            if (binding.viewPager.currentItem + 1 < onBoardingAdapter.itemCount) {
                binding.viewPager.currentItem = binding.viewPager.currentItem + 1
            } else {
                Prefs.putBoolean(IS_ON_BOARDING_SCREENS_VISITED, true)
                startActivity(Intent(applicationContext, DashboardActivity::class.java))
                finish()
            }
        }
    }

    override fun setOnBoardingData(onBoardingList: List<OnBoardingModel>) {
        onBoardingAdapter = OnBoardingAdapter(onBoardingList)
        viewPager.adapter = onBoardingAdapter
        viewPager.registerOnPageChangeCallback(onPageChangeListener)
        setupIndicator()
        setupCurrentIndicator(0)
    }

    private fun getOnBoardingList() = ArrayList<OnBoardingModel>().apply {
        getDrawable(R.drawable.ic_mask_woman)?.let {
            OnBoardingModel(
                it,
                title = getString(R.string.wear_mask),
                description = getString(R.string.wear_mask_desc)
            ).apply { add(this) }
        }
        getDrawable(R.drawable.ic_remote_work_man)?.let {
            OnBoardingModel(
                it,
                title = getString(R.string.work_from_home),
                description = getString(R.string.work_from_home_desc)
            ).apply { add(this) }
        }
        getDrawable(R.drawable.ic_doctor_woman)?.let {
            OnBoardingModel(
                it,
                title = getString(R.string.consult_with_doctor),
                description = getString(R.string.consult_with_doctor_desc)
            ).apply { add(this) }
        }
    }

    private val onPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setupCurrentIndicator(position)
        }
    }

    private fun setupIndicator() {
        val indicator: Array<ImageView?> = arrayOfNulls(onBoardingAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            24, 24
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_circle_placeholder
                )
            )
            indicator[i]?.layoutParams = layoutParams
            indicatorOverlay.addView(indicator[i])
        }
    }

    private fun setupCurrentIndicator(index: Int) {
        val itemChildCount: Int = indicatorOverlay.childCount
        for (i in 0 until itemChildCount) {
            val imageView: ImageView = indicatorOverlay.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_circle
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(this, R.color.blue),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )

            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_circle_placeholder
                    )
                )
                imageView.setColorFilter(
                    ContextCompat.getColor(this, R.color.dark_gray),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }
        if (index == onBoardingAdapter.itemCount - 1) {
            onBoardingBtn.text = getString(R.string.start)
        } else {
            onBoardingBtn.text = getString(R.string.next)
        }
    }

    companion object {
        const val IS_ON_BOARDING_SCREENS_VISITED = "isOnBoardingScreensSeen"
    }
}
