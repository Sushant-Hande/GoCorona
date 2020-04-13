package com.sushanthande.gocorona.ui.covidupdate

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.CountryDataAdapter
import com.sushanthande.gocorona.databinding.CovidUpdateActivityBinding
import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import com.sushanthande.gocorona.ui.covidupdate.countrydetails.CountryDetailsActivity
import kotlinx.android.synthetic.main.layout_shimmer.view.*

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CovidUpdateActivity : BaseActivity(), CovidUpdateContract.View,
    CountryDataAdapter.CountryClickListener {

    private lateinit var binding: CovidUpdateActivityBinding
    private lateinit var presenter: CovidUpdateContract.Presenter
    private lateinit var countryDataAdapter: CountryDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.covid_update_activity)
        setSupportActionBar(binding.toolBar)
        presenter = CovidUpdatePresenterImpl(this)
        if (isNetworkAvailable(this)) {
            getCovidUpdate()
        } else {
            binding.svParent.visibility = GONE
            binding.groupCheckInternet.visibility = GONE
        }
        events()
    }

    private fun events() {
        binding.toolBar.setNavigationOnClickListener { finish() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                countryDataAdapter.filterList(s.toString().trim())
                if (s.toString().isNotEmpty()) {
                    binding.ivClose.visibility = VISIBLE
                } else {
                    binding.ivClose.visibility = INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.ivClose.setOnClickListener {
            binding.etSearch.text?.clear()
        }

        binding.btnRetry.setOnClickListener {
            if (isNetworkAvailable(this)) {
                binding.groupCheckInternet.visibility = GONE
                getCovidUpdate()
            }
        }
    }

    fun getCovidUpdate() {
        binding.svParent.visibility = GONE
        binding.layoutShimmer.visibility = VISIBLE
        binding.layoutShimmer.shimmerContainer.animate()
        presenter.getCovidUpdate()
        presenter.getAllCountryData()
    }

    override fun setCovidUpdate(globalDataModel: GlobalDataModel) {
        binding.svParent.visibility = VISIBLE
        binding.layoutShimmer.shimmerContainer.stopShimmer()
        binding.layoutShimmer.shimmerContainer.visibility = GONE
        binding.childLayout.visibility = VISIBLE
    }

    override fun setAllCountryData(countryDataList: List<CountryDataModel>) {
        countryDataAdapter = CountryDataAdapter(countryDataList, this)
        binding.rcCountryData.layoutManager = LinearLayoutManager(this)
        binding.rcCountryData.adapter = countryDataAdapter
    }

    override fun onGetDataFailed() {
        binding.svParent.visibility = VISIBLE
        binding.layoutShimmer.shimmerContainer.stopShimmer()
        binding.layoutShimmer.shimmerContainer.visibility = GONE
    }

    override fun onCountryClick(countryDataModel: CountryDataModel) {
        val intent = Intent(this, CountryDetailsActivity::class.java)
        intent.putExtra(CountryDetailsActivity.COUNTRY_OBJECT, countryDataModel)
        startActivity(intent)
    }
}
