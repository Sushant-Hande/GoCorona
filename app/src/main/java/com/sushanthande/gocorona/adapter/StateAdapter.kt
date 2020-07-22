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
import com.sushanthande.gocorona.databinding.StateItemBinding

import com.sushanthande.gocorona.model.StateModel

/**
 *Created by Sushant Hande on 11-04-2020
 */
class StateAdapter(private val stateList: List<StateModel>) :
    RecyclerView.Adapter<StateAdapter.ViewHolder>() {

    private var filteredDataList: List<StateModel> = stateList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val stateItemBinding: StateItemBinding =
            StateItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(stateItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredDataList[position])
    }

    override fun getItemCount(): Int = filteredDataList.size

    class ViewHolder(private val stateItemBinding: StateItemBinding) :
        RecyclerView.ViewHolder(stateItemBinding.root) {

        fun bind(stateModel: StateModel) {
            stateItemBinding.state = stateModel
            stateItemBinding.executePendingBindings()
        }
    }

    fun filterList(queryString: String) {
        filteredDataList = stateList.filter { it.state.contains(queryString, true) }
        notifyDataSetChanged()
    }
}
