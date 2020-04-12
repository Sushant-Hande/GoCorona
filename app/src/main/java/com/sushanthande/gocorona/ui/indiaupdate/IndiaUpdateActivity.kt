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
import com.sushanthande.gocorona.databinding.ActivityIndiaUpdateBinding
import com.sushanthande.gocorona.model.StateModel
import kotlinx.android.synthetic.main.activity_india_update.*
import kotlinx.android.synthetic.main.layout_shimmer.view.*


/**
 *Created by Sushant Hande on 11-04-2020
 */
class IndiaUpdateActivity : BaseActivity(), IndiaUpdateContract.View {

    private lateinit var binding: ActivityIndiaUpdateBinding
    lateinit var presenter: IndiaUpdateContract.Presenter
    lateinit var stateAdapter: StateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_india_update)
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
        rvState.adapter = stateAdapter
        rvState.layoutManager = LinearLayoutManager(this)
        showParentView()
        hideShimmerView()
    }

    override fun showParentView() {
        parentLayout.visibility = VISIBLE
    }

    override fun hideParentView() {
        parentLayout.visibility = GONE
    }

    override fun showShimmerView() {
        layoutShimmer.visibility = VISIBLE
        layoutShimmer.shimmerContainer.startShimmer()
    }

    override fun hideShimmerView() {
        layoutShimmer.shimmerContainer.stopShimmer()
        layoutShimmer.visibility = GONE
    }

    override fun showCheckInternetView() {
        groupCheckInternet.visibility = VISIBLE
    }

    private fun hideCheckInternetView() {
        groupCheckInternet.visibility = GONE
    }

    override fun onRetryClick() {
        if (isNetworkAvailable(this)) {
            hideCheckInternetView()
            presenter.getIndiaUpdate()
        }
    }

    private fun events() {
        toolBar.setNavigationOnClickListener { finish() }

        etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                stateAdapter.filterList(s.toString().trim())
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
            presenter.onRetryClick()
        }
    }
}
