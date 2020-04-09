package com.sushanthande.gocorona.ui.symptoms

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.sushanthande.gocorona.BaseActivity
import com.sushanthande.gocorona.BuildConfig
import com.sushanthande.gocorona.R
import com.sushanthande.gocorona.adapter.CommonDataAdapter
import com.sushanthande.gocorona.model.CommonDataModel
import kotlinx.android.synthetic.main.activity_symptoms.*

/**
 *Created by Sushant Hande on 29-03-2020
 */
class SymptomsActivity : BaseActivity(), SymptomsContract.View {

    private lateinit var symptomAdapter: CommonDataAdapter
    private lateinit var presenter: SymptomsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)
        setSupportActionBar(toolBar)
        toolBar.setNavigationOnClickListener { finish() }
        presenter = SymptomsPresenterImpl(this)
        presenter.setSymptomsList(getSymptomsList())
    }

    override fun setSymptomsList(symptomsList: List<CommonDataModel>) {
        symptomAdapter = CommonDataAdapter(symptomsList)
        rvSymptoms.adapter = symptomAdapter
        rvSymptoms.layoutManager = LinearLayoutManager(this)
    }

    private fun getSymptomsList() = ArrayList<CommonDataModel>().apply {
        val symptom1 = CommonDataModel(
            dataString = getString(R.string.symptom1),
            image = getDrawable(R.drawable.ic_fever)
        )
        add(symptom1)

        val symptom2 = CommonDataModel(
            dataString = getString(R.string.symptom2),
            image = getDrawable(R.drawable.ic_cough)
        )
        add(symptom2)

        val symptom3 = CommonDataModel(
            dataString = getString(R.string.symptom3),
            image = getDrawable(R.drawable.ic_cough)
        )
        add(symptom3)

        val symptom4 = CommonDataModel(
            dataString = getString(R.string.symptom4),
            image = getDrawable(R.drawable.ic_tired)
        )
        add(symptom4)

        val symptom5 = CommonDataModel(
            dataString = getString(R.string.symptom5),
            image = getDrawable(R.drawable.ic_cough)
        )
        add(symptom5)

        val symptom6 = CommonDataModel(
            dataString = getString(R.string.symptom6),
            image = getDrawable(R.drawable.ic_nose)
        )
        add(symptom6)

        val symptom7 = CommonDataModel(
            dataString = getString(R.string.symptom7),
            image = getDrawable(R.drawable.ic_sorethroat)
        )
        add(symptom7)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_SUBJECT,
                        R.string.app_name
                    )
                    var shareMessage =
                        "${getString(R.string.covid19)}${getString(
                            R.string.symptoms
                        )}\n1.${getString(
                            R.string.symptom1
                        )}\n2.${getString(
                            R.string.symptom2
                        )}\n3.${getString(
                            R.string.symptom3
                        )}\n4.${getString(R.string.symptom4)}\n5.${getString(
                            R.string.symptom5
                        )}\n6.${getString(
                            R.string.symptom6
                        )}\n7.${getString(R.string.symptom7)}\n\n"
                    shareMessage =
                        """
                    ${shareMessage}Shared by https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                    """.trimIndent()
                    putExtra(Intent.EXTRA_TEXT, shareMessage)
                }
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
