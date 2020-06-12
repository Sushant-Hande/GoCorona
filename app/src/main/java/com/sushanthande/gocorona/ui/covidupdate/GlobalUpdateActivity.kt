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
import com.sushanthande.gocorona.databinding.GlobalUpdateActivityBinding
import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import com.sushanthande.gocorona.ui.covidupdate.countrydetails.CountryDetailsActivity

/**
 *Created by Sushant Hande on 07-04-2020
 */
class GlobalUpdateActivity : BaseActivity(), CovidUpdateContract.View,
    CountryDataAdapter.CountryClickListener {

    private lateinit var binding: GlobalUpdateActivityBinding
    private lateinit var presenter: CovidUpdateContract.Presenter
    private lateinit var countryDataAdapter: CountryDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.global_update_activity)
        setSupportActionBar(binding.toolBar)
        presenter = CovidUpdatePresenterImpl(this)
        if (isNetworkAvailable(this)) {
            presenter.getGlobalAndAllCountryUpdates()
        } else {
            hideParentView()
            showCheckInternetView()
        }
        events()
    }

    override fun showParentView() {
        binding.svParent.visibility = VISIBLE
    }

    override fun hideParentView() {
        binding.svParent.visibility = GONE
    }

    override fun hideProgressBar() {
        binding.progressHorizontal.visibility = GONE
    }

    override fun showProgressBar() {
        binding.progressHorizontal.visibility = VISIBLE
        binding.progressHorizontal.animate()
    }

    override fun showCheckInternetView() {
        binding.groupCheckInternet.visibility = VISIBLE
    }

    override fun onRetryClick() {
        if (isNetworkAvailable(this)) {
            presenter.getGlobalAndAllCountryUpdates()
            hideCheckInternetView()
        }
    }

    private fun hideCheckInternetView() {
        binding.groupCheckInternet.visibility = GONE
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
                presenter.getGlobalAndAllCountryUpdates()
            }
        }
    }

    override fun setCovidUpdate(globalDataModel: GlobalDataModel) {
        binding.globalDataModel = globalDataModel
    }

    override fun setAllCountryData(countryDataList: List<CountryDataModel>) {
        countryDataAdapter = CountryDataAdapter(countryDataList, this)
        binding.rcCountryData.layoutManager = LinearLayoutManager(this)
        binding.rcCountryData.adapter = countryDataAdapter
    }

    override fun onCountryClick(countryDataModel: CountryDataModel) {
        val intent = Intent(this, CountryDetailsActivity::class.java)
        intent.putExtra(CountryDetailsActivity.COUNTRY_OBJECT, countryDataModel)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.clear()
        super.onDestroy()
    }
}
