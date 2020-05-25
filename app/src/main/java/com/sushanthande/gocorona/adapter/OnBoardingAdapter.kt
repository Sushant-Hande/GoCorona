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
