package com.sushanthande.gocorona.ui.onboarding

import com.sushanthande.gocorona.model.OnBoardingModel

/**
 *Created by Sushant Hande on 18-05-2020
 */
class OnBoardingPresenterImpl(val view: OnBoardingContract.View) : OnBoardingContract.Presenter {

    override fun setOnBoardingData(onBoardingList: List<OnBoardingModel>) {
        view.setOnBoardingData(onBoardingList)
    }
}