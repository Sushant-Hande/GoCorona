package com.sushanthande.gocorona.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sushanthande.gocorona.databinding.CommonItemBinding
import com.sushanthande.gocorona.model.CommonDataModel

/**
 *Created by Sushant Hande on 03-04-2020
 */
class CommonDataAdapter(private val dataList: List<CommonDataModel>) :
    RecyclerView.Adapter<CommonDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: CommonItemBinding =
            CommonItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    class ViewHolder(private val commonItemBinding: CommonItemBinding) :
        RecyclerView.ViewHolder(commonItemBinding.root) {

        fun bind(commonDataModel: CommonDataModel) {
            commonItemBinding.data = commonDataModel
            commonItemBinding.executePendingBindings()
        }
    }
}