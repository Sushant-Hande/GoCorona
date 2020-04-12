package com.sushanthande.gocorona.ui.covidupdate

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.CountryDataAdapter
import com.sushanthande.gocorona.ui.covidupdate.countrydetails.CountryDetailsActivity
import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import kotlinx.android.synthetic.main.activity_covid_update.*
import kotlinx.android.synthetic.main.activity_covid_update.etSearch
import kotlinx.android.synthetic.main.activity_covid_update.rcCountryData
import kotlinx.android.synthetic.main.activity_covid_update.tvDeaths
import kotlinx.android.synthetic.main.activity_covid_update.tvRecovered
import kotlinx.android.synthetic.main.activity_covid_update.tvTotalCases
import kotlinx.android.synthetic.main.layout_shimmer.*

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CovidUpdateActivity : BaseActivity(), CovidUpdateContract.View,
    CountryDataAdapter.CountryClickListener {

    private lateinit var presenter: CovidUpdateContract.Presenter
    private lateinit var countryDataAdapter: CountryDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_update)
        setSupportActionBar(toolBar)
        presenter = CovidUpdatePresenterImpl(this)
        if (isNetworkAvailable(this)) {
            getCovidUpdate()
        } else {
            svParent.visibility = GONE
            groupCheckInternet.visibility = GONE
        }
        events()
    }

    private fun events() {
        toolBar.setNavigationOnClickListener { finish() }

        etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                countryDataAdapter.filterList(s.toString().trim())
                if (s.toString().isNotEmpty()) {
                    ivClose.visibility = VISIBLE
                } else {
                    ivClose.visibility = INVISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        ivClose.setOnClickListener {
            etSearch.text?.clear()
        }

        btnRetry.setOnClickListener {
            if (isNetworkAvailable(this)) {
                groupCheckInternet.visibility = GONE
                getCovidUpdate()
            }
        }
    }

    fun getCovidUpdate() {
        svParent.visibility = GONE
        layoutShimmer.visibility = VISIBLE
        shimmerContainer.animate()
        presenter.getCovidUpdate()
        presenter.getAllCountryData()
    }

    override fun setCovidUpdate(globalDataModel: GlobalDataModel) {
        svParent.visibility = VISIBLE
        shimmerContainer.stopShimmer()
        shimmerContainer.visibility = GONE
        childLayout.visibility = VISIBLE
        tvTotalCases.text = globalDataModel.cases.toString()
        tvRecovered.text = globalDataModel.recovered.toString()
        tvDeaths.text = globalDataModel.deaths.toString()
    }

    override fun setAllCountryData(countryDataList: List<CountryDataModel>) {
        countryDataAdapter = CountryDataAdapter(countryDataList, this)
        rcCountryData.layoutManager = LinearLayoutManager(this)
        rcCountryData.adapter = countryDataAdapter
    }

    override fun onGetDataFailed(){
        svParent.visibility = VISIBLE
        shimmerContainer.stopShimmer()
        shimmerContainer.visibility = GONE
    }

    override fun onCountryClick(countryDataModel: CountryDataModel) {
        val intent = Intent(this, CountryDetailsActivity::class.java)
        intent.putExtra(CountryDetailsActivity.COUNTRY_OBJECT, countryDataModel)
        startActivity(intent)
    }
}
