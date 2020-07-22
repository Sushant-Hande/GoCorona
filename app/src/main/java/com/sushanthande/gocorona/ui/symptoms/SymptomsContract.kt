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