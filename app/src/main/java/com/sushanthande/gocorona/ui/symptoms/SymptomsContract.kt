package com.sushanthande.gocorona.ui.symptoms

import com.sushanthande.gocorona.model.CommonDataModel

/**
 *Created by Sushant Hande on 29-03-2020
 */
interface SymptomsContract {

    interface View {
        fun setSymptomsList(symptomsList:List<CommonDataModel>)
    }

    interface Presenter {
        fun setSymptomsList(symptomsList:List<CommonDataModel>)
    }
}