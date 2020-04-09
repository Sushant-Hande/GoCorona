package com.sushanthande.gocorona.ui.precautions

import com.sushanthande.gocorona.model.CommonDataModel

/**
 *Created by Sushant Hande on 03-04-2020
 */
interface PrecautionsContract {

    interface View {
        fun setPrecautionsList(precautionsList:List<CommonDataModel>)
    }

    interface Presenter {
        fun setPrecautionsList(precautionsList:List<CommonDataModel>)
    }

}