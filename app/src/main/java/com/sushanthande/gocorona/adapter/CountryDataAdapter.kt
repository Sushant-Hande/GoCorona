package com.sushanthande.gocorona.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.model.CountryDataModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_country.view.*

/**
 *Created by Sushant Hande on 07-04-2020
 */
class CountryDataAdapter(
    private val dataList: List<CountryDataModel>,
    val countryClickListener: CountryClickListener
) :
    RecyclerView.Adapter<CountryDataAdapter.ViewHolder>() {

    private var filteredDataList: List<CountryDataModel> = dataList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_country,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = filteredDataList[position]
        holder.ivFlag.load(data.countryInfo?.flag) {
            placeholder(R.drawable.ic_circle_placeholder)
        }
        holder.tvCountryName.text = data.country
        holder.tvTodayCase.text = data.todayCases.toString()
        holder.tvTotalCases.text = data.cases.toString()
        holder.tvRecovered.text = data.recovered.toString()
        holder.tvDeaths.text = data.deaths.toString()
        holder.clParent.setOnClickListener { countryClickListener.onCountryClick(data) }
    }

    override fun getItemCount() = filteredDataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFlag: CircleImageView = itemView.ivFlag
        val tvCountryName: AppCompatTextView = itemView.tvCountryName
        val tvTodayCase: AppCompatTextView = itemView.tvTodayCases
        val tvTotalCases: AppCompatTextView = itemView.tvTotalCases
        val tvRecovered: AppCompatTextView = itemView.tvRecovered
        val tvDeaths: AppCompatTextView = itemView.tvDeaths
        val clParent: ConstraintLayout = itemView.clParent
    }

    fun filterList(queryString: String) {
        filteredDataList = dataList.filter { it.country.contains(queryString, true) }
        notifyDataSetChanged()
    }

    interface CountryClickListener {
        fun onCountryClick(countryDataModel: CountryDataModel)
    }
}