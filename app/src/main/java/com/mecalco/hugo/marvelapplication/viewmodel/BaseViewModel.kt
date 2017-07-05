package com.mecalco.hugo.marvelapplication.viewmodel

import com.mecalco.hugo.marvelapplication.viewmodel.view.IView
import io.reactivex.disposables.CompositeDisposable

/**
 * @author by hugo on 5/24/17.
 */

open class BaseViewModel<T : IView> {

    var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    var mView: T? = null

    fun attach(view: T) {
        mView = view
    }

    fun detach() {
        mView = null
    }

    fun clearSubscriptions() {
        mCompositeDisposable.clear()
    }

}