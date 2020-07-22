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

package com.sushanthande.gocorona.ui.precautions

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.CommonDataAdapter
import com.sushanthande.gocorona.databinding.PrecautionsActivityBinding
import com.sushanthande.gocorona.model.CommonDataModel

/**
 *Created by Sushant Hande on 03-04-2020
 */
class PrecautionsActivity : BaseActivity(), PrecautionsContract.View {

    private lateinit var binding: PrecautionsActivityBinding
    private lateinit var precautionDataAdapter: CommonDataAdapter
    private lateinit var presenter: PrecautionsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.precautions_activity)
        setSupportActionBar(binding.toolBar)
        binding.toolBar.setNavigationOnClickListener { finish() }
        presenter = PrecautionsPresenterImpl(this)
        presenter.setPrecautionsList(getPrecautionsList())
    }

    override fun setPrecautionsList(precautionsList: List<CommonDataModel>) {
        precautionDataAdapter = CommonDataAdapter(precautionsList)
        binding.rvPrecautions.adapter = precautionDataAdapter
        binding.rvPrecautions.layoutManager = LinearLayoutManager(this)
    }

    private fun getPrecautionsList() = ArrayList<CommonDataModel>().apply {
        val precaution1 = CommonDataModel(
            dataString = getString(R.string.precaution1),
            image = getDrawable(R.drawable.ic_distance)
        )
        add(precaution1)

        val precaution2 = CommonDataModel(
            dataString = getString(R.string.precaution2),
            image = getDrawable(R.drawable.ic_washhands)
        )
        add(precaution2)

        val precaution3 = CommonDataModel(
            dataString = getString(R.string.precaution3),
            image = getDrawable(R.drawable.ic_distance)
        )
        add(precaution3)

        val precaution4 = CommonDataModel(
            dataString = getString(R.string.precaution4),
            image = getDrawable(R.drawable.ic_distance)
        )
        add(precaution4)

        val precaution5 = CommonDataModel(
            dataString = getString(R.string.precaution5),
            image = getDrawable(R.drawable.ic_cough)
        )
        add(precaution5)
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
                    putExtra(
                        Intent.EXTRA_TITLE,
                        R.string.app_name
                    )
                    val shareMessage =
                        "\n${getString(R.string.covid19)}${getString(
                            R.string.precautions
                        )}\n1.${getString(
                            R.string.precaution1
                        )}\n2.${getString(
                            R.string.precaution2
                        )}\n3.${getString(
                            R.string.precaution3
                        )}\n4.${getString(R.string.precaution4)}\n5.${getString(
                            R.string.precaution5
                        )}\n\n"
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
