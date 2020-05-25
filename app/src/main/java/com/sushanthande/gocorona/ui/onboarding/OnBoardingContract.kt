package com.sushanthande.gocorona.ui.onboarding

import com.sushanthande.gocorona.model.OnBoardingModel

/**
 *Created by Sushant Hande on 18-05-2020
 */
interface OnBoardingContract {

    interface View {
        fun setOnBoardingData(onBoardingList: List<OnBoardingModel>)
    }

    interface Presenter {
        fun setOnBoardingData(onBoardingList: List<OnBoardingModel>)
    }
}