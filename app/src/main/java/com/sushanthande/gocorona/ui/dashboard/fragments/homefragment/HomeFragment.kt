package com.sushanthande.gocorona.ui.dashboard.fragments.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.DashboardMenuAdapter
import com.sushanthande.gocorona.databinding.HomeFragmentBinding
import com.sushanthande.gocorona.model.DashboardMenu
import com.sushanthande.gocorona.ui.covidupdate.GlobalUpdateActivity
import com.sushanthande.gocorona.ui.indiaupdate.IndiaUpdateActivity
import com.sushanthande.gocorona.ui.precautions.PrecautionsActivity
import com.sushanthande.gocorona.ui.symptoms.SymptomsActivity

/**
 *Created by Sushant Hande on 25-03-2020
 */
class HomeFragment : Fragment(), HomeFragmentContract.View, DashboardMenuAdapter.MenuClickListener {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var presenter: HomeFragmentContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomeFragmentPresenterImpl(this)
        presenter.setDashboardMenu(getMenuList())
    }

    override fun setDashboardMenu(menuList: ArrayList<DashboardMenu>) {
        binding.rvMenu.adapter = DashboardMenuAdapter(getMenuList(), this)
        binding.rvMenu.layoutManager = GridLayoutManager(activity, 2)
    }

    override fun onMenuClick(menuId: Int) {
        when (menuId) {

            DashboardMenu.Menu.INDIA_UPDATES.id -> {
                startActivity(Intent(activity, IndiaUpdateActivity::class.java))
            }

            DashboardMenu.Menu.WORLD_UPDATES.id -> {
                startActivity(Intent(activity, GlobalUpdateActivity::class.java))
            }

            DashboardMenu.Menu.SYMPTOMS.id -> {
                startActivity(Intent(activity, SymptomsActivity::class.java))
            }

            DashboardMenu.Menu.PRECAUTIONS.id -> {
                startActivity(Intent(activity, PrecautionsActivity::class.java))
            }
        }
    }

    private fun getMenuList() = ArrayList<DashboardMenu>().apply {
        val indiaUpdateMenu = DashboardMenu()
        indiaUpdateMenu.id = DashboardMenu.Menu.INDIA_UPDATES.id
        indiaUpdateMenu.name = getString(R.string.india_updates)
        indiaUpdateMenu.image = R.drawable.ic_india
        add(indiaUpdateMenu)

        val coronaUpdateMenu = DashboardMenu()
        coronaUpdateMenu.id = DashboardMenu.Menu.WORLD_UPDATES.id
        coronaUpdateMenu.name = getString(R.string.global_updates)
        coronaUpdateMenu.image = R.drawable.ic_world
        add(coronaUpdateMenu)

        val symptomsMenu = DashboardMenu()
        symptomsMenu.id = DashboardMenu.Menu.SYMPTOMS.id
        symptomsMenu.name = getString(R.string.symptoms)
        symptomsMenu.image = R.drawable.ic_cough
        add(symptomsMenu)

        val precautionsMenu = DashboardMenu()
        precautionsMenu.id = DashboardMenu.Menu.PRECAUTIONS.id
        precautionsMenu.name = getString(R.string.precautions)
        precautionsMenu.image = R.drawable.ic_air_mask
        add(precautionsMenu)
    }
}
