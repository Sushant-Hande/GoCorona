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
