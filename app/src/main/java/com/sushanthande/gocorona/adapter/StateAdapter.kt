package com.sushanthande.gocorona.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sushanthande.gocorona.databinding.ItemStateBinding

import com.sushanthande.gocorona.model.StateModel

/**
 *Created by Sushant Hande on 11-04-2020
 */
class StateAdapter(private val stateList: List<StateModel>) :
    RecyclerView.Adapter<StateAdapter.ViewHolder>() {

    private var filteredDataList: List<StateModel> = stateList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemStateBinding = ItemStateBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredDataList[position])
    }

    override fun getItemCount(): Int = filteredDataList.size

    class ViewHolder(private val binding: ItemStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(stateModel: StateModel) {
            binding.state = stateModel
            binding.executePendingBindings()
        }
    }

    fun filterList(queryString: String) {
        filteredDataList = stateList.filter { it.state.contains(queryString, true) }
        notifyDataSetChanged()
    }
}
