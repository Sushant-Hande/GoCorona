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
