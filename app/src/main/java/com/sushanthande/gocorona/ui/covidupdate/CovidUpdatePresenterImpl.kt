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

package com.sushanthande.gocorona.ui.covidupdate

import com.sushanthande.gocorona.model.CountryDataModel
import com.sushanthande.gocorona.model.GlobalCountryDataModel
import com.sushanthande.gocorona.model.GlobalDataModel
import com.sushanthande.gocorona.network.ApiClient
import com.sushanthande.gocorona.network.ApiRequests
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 *Created by Sushant Hande on 07-04-2020
 */
class CovidUpdatePresenterImpl(val view: CovidUpdateContract.View) : CovidUpdateContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getGlobalAndAllCountryUpdates() {
        view.hideParentView()
        view.showProgressBar()
        val apiRequest = ApiClient.createGlobalService(ApiRequests.Global::class.java)

        val globalObservable = apiRequest.getGlobalData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val countryObservable = apiRequest.getAllCountryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable: Disposable = Observable.zip(
                globalObservable,
                countryObservable,
                BiFunction<GlobalDataModel, List<CountryDataModel>, GlobalCountryDataModel> { globalDataModel, countryDataList ->
                    return@BiFunction getCombinedData(globalDataModel, countryDataList)
                })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<GlobalCountryDataModel>() {
                override fun onNext(globalCountryDataModel: GlobalCountryDataModel) {
                    view.hideProgressBar()
                    view.showParentView()
                    view.setCovidUpdate(globalCountryDataModel.globalDataModel)
                    view.setAllCountryData(globalCountryDataModel.countryDataList)
                }

                override fun onError(e: Throwable) {
                    view.hideProgressBar()
                    view.showCheckInternetView()
                }

                override fun onComplete() {}
            })

        compositeDisposable.add(disposable)
    }

    private fun getCombinedData(
        globalDataModel: GlobalDataModel,
        countryDataList: List<CountryDataModel>
    ): GlobalCountryDataModel {
        return GlobalCountryDataModel(
            globalDataModel = globalDataModel,
            countryDataList = countryDataList
        )
    }

    override fun clear() {
        compositeDisposable.dispose()
    }
}
