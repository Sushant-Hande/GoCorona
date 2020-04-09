package com.sushanthande.gocorona.ui.precautions

import com.sushanthande.gocorona.model.CommonDataModel

/**
 *Created by Sushant Hande on 03-04-2020
 */
class PrecautionsPresenterImpl(val view:PrecautionsContract.View):PrecautionsContract.Presenter {

    override fun setPrecautionsList(precautionsList: List<CommonDataModel>) {
        view.setPrecautionsList(precautionsList)
    }
}