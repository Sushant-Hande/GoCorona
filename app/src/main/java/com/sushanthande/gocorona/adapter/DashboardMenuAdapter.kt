package com.sushanthande.gocorona.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.model.DashboardMenu
import kotlinx.android.synthetic.main.item_dashboard.view.*

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardMenuAdapter(
    private val menuList: List<DashboardMenu>,
    private val menuClickListener: MenuClickListener
) :
    RecyclerView.Adapter<DashboardMenuAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dashboard,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu = menuList[position]
        holder.tvMenu.text = menu.name
        holder.ivMenu.setImageDrawable(menu.image)
        holder.ivMenu.setColorFilter(
            ContextCompat.getColor(context, R.color.blue), android.graphics.PorterDuff.Mode.SRC_IN
        )
        holder.ivMenuParent.supportBackgroundTintList =
            ContextCompat.getColorStateList(context, R.color.blue_transparent)
        holder.cvMenu.setOnClickListener {
            menuClickListener.onMenuClick(menu.id)
        }
    }

    override fun getItemCount() = menuList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMenu: AppCompatImageView = itemView.ivMenu
        val tvMenu: AppCompatTextView = itemView.tvMenu
        val cvMenu: CardView = itemView.cvMenu
        val ivMenuParent = itemView.ivMenuParent
    }

    interface MenuClickListener {
        fun onMenuClick(menuId: Int)
    }
}
