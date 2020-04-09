package com.sushanthande.gocorona.ui.symptoms

import com.sushanthande.gocorona.model.CommonDataModel

/**
 *Created by Sushant Hande on 29-03-2020
 */
class SymptomsPresenterImpl(val view: SymptomsContract.View) : SymptomsContract.Presenter {

    override fun setSymptomsList(symptomsList: List<CommonDataModel>) {
        view.setSymptomsList(symptomsList)
    }
}