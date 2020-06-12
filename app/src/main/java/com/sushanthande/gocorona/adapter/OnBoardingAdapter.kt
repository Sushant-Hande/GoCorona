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
import com.sushanthande.gocorona.databinding.OnboardingItemBinding
import com.sushanthande.gocorona.model.OnBoardingModel

/**
 *Created by Sushant Hande on 18-05-2020
 */
class OnBoardingAdapter(private val onBoardingList: List<OnBoardingModel>) :
    RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val onBoardingItemBinding: OnboardingItemBinding =
            OnboardingItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            onBoardingItemBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onBoardingList[position])
    }

    override fun getItemCount(): Int = onBoardingList.size

    class ViewHolder(private val onBoardingItemBinding: OnboardingItemBinding) :
        RecyclerView.ViewHolder(onBoardingItemBinding.root) {

        fun bind(onBoardingModel: OnBoardingModel) {
            onBoardingItemBinding.onBoardingModel = onBoardingModel
            onBoardingItemBinding.onBoardingImage.load(onBoardingModel.image)
        }
    }
}
