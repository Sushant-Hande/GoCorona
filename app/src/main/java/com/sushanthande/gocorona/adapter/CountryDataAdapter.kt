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
        holder.bind(countryDataModelList[position], countryClickListener)
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