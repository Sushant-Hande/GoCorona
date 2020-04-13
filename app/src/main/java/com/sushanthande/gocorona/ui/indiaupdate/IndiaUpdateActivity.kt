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
import kotlinx.android.synthetic.main.layout_shimmer.view.*


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

    override fun setIndiaUpdate(stateModelList: ArrayList<StateModel>) {
        val totalObject = stateModelList[0]
        binding.total = totalObject
        stateModelList.removeAt(0)
        stateAdapter = StateAdapter(stateModelList)
        binding. rvState.adapter = stateAdapter
        binding.rvState.layoutManager = LinearLayoutManager(this)
        showParentView()
        hideShimmerView()
    }

    override fun showParentView() {
        binding.parentLayout.visibility = VISIBLE
    }

    override fun hideParentView() {
        binding.parentLayout.visibility = GONE
    }

    override fun showShimmerView() {
        binding.layoutShimmer.visibility = VISIBLE
        binding.layoutShimmer.shimmerContainer.startShimmer()
    }

    override fun hideShimmerView() {
        binding.layoutShimmer.shimmerContainer.stopShimmer()
        binding.layoutShimmer.visibility = GONE
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
}
