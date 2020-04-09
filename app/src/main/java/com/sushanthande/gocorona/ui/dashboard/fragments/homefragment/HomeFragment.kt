package com.sushanthande.gocorona.ui.dashboard.fragments.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.DashboardMenuAdapter
import com.sushanthande.gocorona.model.DashboardMenu
import com.sushanthande.gocorona.ui.covid.WashHandsActivity
import com.sushanthande.gocorona.ui.covidupdate.CovidUpdateActivity
import com.sushanthande.gocorona.ui.precautions.PrecautionsActivity
import com.sushanthande.gocorona.ui.symptoms.SymptomsActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *Created by Sushant Hande on 25-03-2020
 */
class HomeFragment : Fragment(), HomeFragmentContract.View, DashboardMenuAdapter.MenuClickListener {

    private lateinit var presenter: HomeFragmentContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomeFragmentPresenterImpl(this)
        presenter.setDashboardMenu(getMenuList())
    }

    override fun setDashboardMenu(menuList: ArrayList<DashboardMenu>) {
        rvMenu.adapter = DashboardMenuAdapter(getMenuList(), this)
        rvMenu.layoutManager = GridLayoutManager(activity, 2)
    }

    override fun onMenuClick(menuId: Int) {
        when (menuId) {

            DashboardMenu.Menu.CORONA_UPDATES.id -> {
                startActivity(Intent(activity, CovidUpdateActivity::class.java))
            }

            DashboardMenu.Menu.WHAT_IS_CORONA.id -> {
                startActivity(Intent(activity, WashHandsActivity::class.java))
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
        val coronaUpdateMenu = DashboardMenu()
        coronaUpdateMenu.id = DashboardMenu.Menu.CORONA_UPDATES.id
        coronaUpdateMenu.name = getString(R.string.corona_updates)
        coronaUpdateMenu.image = activity?.getDrawable(R.drawable.ic_world)
        context?.let {
            coronaUpdateMenu.tintColor = ContextCompat.getColor(it, R.color.blue)
            coronaUpdateMenu.backgroundTint =
                ContextCompat.getColorStateList(it, R.color.blue_transparent)
        }
        add(coronaUpdateMenu)

        val whatIsCoronaMenu = DashboardMenu()
        whatIsCoronaMenu.id = DashboardMenu.Menu.WHAT_IS_CORONA.id
        whatIsCoronaMenu.name = getString(R.string.covid19)
        whatIsCoronaMenu.image = activity?.getDrawable(R.drawable.ic_virus)
        context?.let {
            whatIsCoronaMenu.tintColor = ContextCompat.getColor(it, R.color.blue)
            whatIsCoronaMenu.backgroundTint =
                ContextCompat.getColorStateList(it, R.color.blue_transparent)
        }
        add(whatIsCoronaMenu)

        val symptomsMenu = DashboardMenu()
        symptomsMenu.id = DashboardMenu.Menu.SYMPTOMS.id
        symptomsMenu.name = getString(R.string.symptoms)
        symptomsMenu.image = activity?.getDrawable(R.drawable.ic_cough)
        context?.let {
            symptomsMenu.tintColor = ContextCompat.getColor(it, R.color.blue)
            symptomsMenu.backgroundTint =
                ContextCompat.getColorStateList(it, R.color.blue_transparent)
        }
        add(symptomsMenu)

        val precautionsMenu = DashboardMenu()
        precautionsMenu.id = DashboardMenu.Menu.PRECAUTIONS.id
        precautionsMenu.name = getString(R.string.precautions)
        precautionsMenu.image = activity?.getDrawable(R.drawable.ic_air_mask)
        context?.let {
            precautionsMenu.tintColor = ContextCompat.getColor(it, R.color.blue)
            precautionsMenu.backgroundTint =
                ContextCompat.getColorStateList(it, R.color.blue_transparent)
        }
        add(precautionsMenu)
    }
}
