package com.sushanthande.gocorona.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.model.CommonDataModel
import kotlinx.android.synthetic.main.item_common.view.*

/**
 *Created by Sushant Hande on 03-04-2020
 */
class CommonDataAdapter(private val dataList: List<CommonDataModel>) :
    RecyclerView.Adapter<CommonDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_common,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = dataList[position]
        holder.ivData.setImageDrawable(menu.image)
        holder.tvData.text = menu.dataString
        holder.cvParent.setOnClickListener {  }
    }

    override fun getItemCount() = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivData: AppCompatImageView = itemView.ivData
        val tvData: AppCompatTextView = itemView.tvData
        val cvParent: CardView = itemView.cvParent
    }
}