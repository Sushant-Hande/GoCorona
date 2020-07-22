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

package com.sushanthande.gocorona.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.databinding.CountryItemBinding
import com.sushanthande.gocorona.model.CountryDataModel

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CountryDataAdapter(
    private val countryDataModelList: List<CountryDataModel>,
    private val countryClickListener: CountryClickListener
) :
    RecyclerView.Adapter<CountryDataAdapter.ViewHolder>() {

    private var filteredDataList: List<CountryDataModel> = countryDataModelList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val countryItemBinding: CountryItemBinding =
            CountryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            countryItemBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredDataList[position], countryClickListener)
    }

    override fun getItemCount() = filteredDataList.size

    class ViewHolder(private val countryItemBinding: CountryItemBinding) :
        RecyclerView.ViewHolder(countryItemBinding.root) {

        fun bind(countryDataModel: CountryDataModel, countryClickListener: CountryClickListener) {
            countryItemBinding.countryDataModel = countryDataModel
            countryItemBinding.countryClickListener = countryClickListener
            countryItemBinding.ivFlag.load(countryDataModel.countryInfo?.flag) {
                placeholder(R.drawable.ic_circle_placeholder)
            }
            countryItemBinding.executePendingBindings()
        }
    }

    fun filterList(queryString: String) {
        filteredDataList = countryDataModelList.filter { it.country.contains(queryString, true) }
        notifyDataSetChanged()
    }

    interface CountryClickListener {
        fun onCountryClick(countryDataModel: CountryDataModel)
    }
}