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

package com.sushanthande.gocorona.ui.covidupdate.countrydetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import coil.api.load
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.databinding.CountryDetailsActivityBinding
import com.sushanthande.gocorona.model.CountryDataModel

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CountryDetailsActivity : AppCompatActivity(), CountryDetailsContract.View {

    private lateinit var binding: CountryDetailsActivityBinding
    private var countryDataModel: CountryDataModel? = null
    lateinit var presenter: CountryDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.country_details_activity)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolBar.setNavigationOnClickListener { finish() }
        presenter = CountryDetailsPresenterImpl(this)
        countryDataModel = intent.getParcelableExtra(COUNTRY_OBJECT)
        countryDataModel?.let {
            presenter.setCountryDetails(it)
        }
    }

    override fun setCountryDetails(countryDataModel: CountryDataModel) {
        binding.countryDataModel = countryDataModel
        binding.countryDataModel?.let {
            binding.ivFlag.load(it.country)
        }
    }

    companion object {
        const val COUNTRY_OBJECT = "countryObject"
    }
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl?.let {
        this.load(imageUrl)
    }
}

@BindingAdapter("totalCases")
fun TextView.setTotalCases(totalCases: String?) {
    this.text =
        if (totalCases.isNullOrEmpty()) "" else "${this.context.getString(R.string.total_cases)} $totalCases"
}

@BindingAdapter("new")
fun TextView.setNewCases(new: String?) {
    this.text =
        if (new.isNullOrEmpty()) "" else "${this.context.getString(R.string.New)} $new"
}

@BindingAdapter("deaths")
fun TextView.setDeaths(deaths: String?) {
    this.text =
        if (deaths.isNullOrEmpty()) "" else "${this.context.getString(R.string.deaths)} $deaths"
}

@BindingAdapter("todayDeaths")
fun TextView.setTodayDeaths(todayDeaths: String?) {
    this.text =
        if (todayDeaths.isNullOrEmpty()) "" else "${this.context.getString(R.string.today_deaths)} $todayDeaths"
}

@BindingAdapter("active")
fun TextView.setActiveCases(active: String?) {
    this.text =
        if (active.isNullOrEmpty()) "" else "${this.context.getString(R.string.active)} $active"
}

@BindingAdapter("critical")
fun TextView.setCriticalCases(critical: String?) {
    this.text =
        if (critical.isNullOrEmpty()) "" else "${this.context.getString(R.string.critical)} $critical"
}

@BindingAdapter("recovered")
fun TextView.setRecoveredCases(recovered: String?) {
    this.text =
        if (recovered.isNullOrEmpty()) "" else "${this.context.getString(R.string.recovered)} $recovered"
}