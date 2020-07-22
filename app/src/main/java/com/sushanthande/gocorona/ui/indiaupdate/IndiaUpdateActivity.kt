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

package com.sushanthande.gocorona.ui.indiaupdate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.StateAdapter
import com.sushanthande.gocorona.databinding.IndiaUpdateActivityBinding
import com.sushanthande.gocorona.model.StateModel

/**
 *Created by Sushant Hande on 11-04-2020
 */
class IndiaUpdateActivity : BaseActivity(), IndiaUpdateContract.View {

    private lateinit var binding: IndiaUpdateActivityBinding
    lateinit var presenter: IndiaUpdateContract.Presenter
    lateinit var stateAdapter: StateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.india_update_activity)
        presenter = IndiaUpdatePresenterImpl(this)
        if (isNetworkAvailable(this)) {
            presenter.getIndiaUpdate()
        } else {
            hideParentView()
            showCheckInternetView()
        }
        events()
    }

    override fun showParentView() {
        binding.parentLayout.visibility = VISIBLE
    }

    override fun hideParentView() {
        binding.parentLayout.visibility = GONE
    }

    override fun showProgressBar() {
        binding.progressHorizontal.visibility = VISIBLE
        binding.progressHorizontal.animate()
    }

    override fun hideProgressBar() {
        binding.progressHorizontal.visibility = GONE
    }

    override fun showCheckInternetView() {
        binding.groupCheckInternet.visibility = VISIBLE
    }

    private fun hideCheckInternetView() {
        binding.groupCheckInternet.visibility = GONE
    }

    override fun onRetryClick() {
        if (isNetworkAvailable(this)) {
            hideCheckInternetView()
            presenter.getIndiaUpdate()
        }
    }

    override fun setIndiaUpdate(stateModelList: ArrayList<StateModel>) {
        val totalObject = stateModelList[0]
        binding.total = totalObject
        stateModelList.removeAt(0)
        stateAdapter = StateAdapter(stateModelList)
        binding.rvState.adapter = stateAdapter
        binding.rvState.layoutManager = LinearLayoutManager(this)
    }

    private fun events() {
        binding.toolBar.setNavigationOnClickListener { finish() }

        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                stateAdapter.filterList(s.toString().trim())
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
            presenter.onRetryClick()
        }
    }

    override fun onDestroy() {
        presenter.clear()
        super.onDestroy()
    }
}
