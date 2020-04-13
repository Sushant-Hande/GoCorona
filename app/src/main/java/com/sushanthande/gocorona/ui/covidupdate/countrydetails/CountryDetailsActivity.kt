package com.sushanthande.gocorona.ui.covidupdate.countrydetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.model.CountryDataModel
import kotlinx.android.synthetic.main.country_details_activity.*

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CountryDetailsActivity : AppCompatActivity(), CountryDetailsContract.View {

    private var countryDataModel: CountryDataModel? = null
    lateinit var presenter: CountryDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_details_activity)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolBar.setNavigationOnClickListener { finish() }
        presenter = CountryDetailsPresenterImpl(this)
        countryDataModel = intent.getParcelableExtra(COUNTRY_OBJECT)
        countryDataModel?.let {
            presenter.setCountryDetails(it)
        }
    }

    override fun setCountryDetails(countryDataModel: CountryDataModel) {
        countryDataModel.let {
            ivFlag.load(it.country)
            tvTotalCases.append(it.cases.toString())
            tvTodayCases.append(it.todayCases.toString())
            tvDeaths.append(it.deaths.toString())
            tvTodayDeaths.append(it.todayDeaths.toString())
            tvActive.append(it.active.toString())
            tvCritical.append(it.critical.toString())
            tvRecovered.append(it.recovered.toString())
        }
    }

    companion object {
        const val COUNTRY_OBJECT = "countryObject"
    }
}
