package com.sushanthande.gocorona.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sushanthande.gocorona.databinding.ItemDashboardBinding
import com.sushanthande.gocorona.model.DashboardMenu

/**
 *Created by Sushant Hande on 25-03-2020
 */
class DashboardMenuAdapter(
    private val menuList: List<DashboardMenu>,
    private val menuClickListener: MenuClickListener
) :
    RecyclerView.Adapter<DashboardMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemDashboardBinding: ItemDashboardBinding =
            ItemDashboardBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            itemDashboardBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position], menuClickListener)
    }

    override fun getItemCount() = menuList.size

    class ViewHolder(private val itemDashboardBinding: ItemDashboardBinding) :
        RecyclerView.ViewHolder(itemDashboardBinding.root) {

        fun bind(dashboardMenu: DashboardMenu, menuClickListener: MenuClickListener) {
            itemDashboardBinding.dashboardMenu = dashboardMenu
            itemDashboardBinding.menuClickListener = menuClickListener
            itemDashboardBinding.executePendingBindings()
        }

    }

    interface MenuClickListener {
        fun onMenuClick(menuId: Int)
    }
}
