package com.sushanthande.gocorona.ui.indiaupdate

import com.sushanthande.gocorona.model.StateModel
import com.sushanthande.gocorona.network.ApiClient
import com.sushanthande.gocorona.network.ApiRequests
import com.sushanthande.gocorona.network.ApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 *Created by Sushant Hande on 11-04-2020
 */
class IndiaUpdatePresenterImpl(val view: IndiaUpdateContract.View) : IndiaUpdateContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getIndiaUpdate() {
        view.hideParentView()
        view.showProgressBar()
        val apiRequest = ApiClient.createIndiaService(ApiRequests.India::class.java)
        val disposable: Disposable = apiRequest.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ApiResponse>() {

                override fun onNext(response: ApiResponse) {
                    view.showParentView()
                    view.hideProgressBar()
                    view.setIndiaUpdate(response.stateList as ArrayList<StateModel>)
                }

                override fun onError(e: Throwable) {
                    view.hideProgressBar()
                    view.hideParentView()
                    view.showCheckInternetView()
                }

                override fun onComplete() {}
            })

        compositeDisposable.add(disposable)
    }

    override fun onRetryClick() {
        view.onRetryClick()
    }

    override fun clear() {
        compositeDisposable.dispose()
    }
}