package com.mecalco.hugo.marvelapplication.viewmodel

import android.util.Log
import com.mecalco.hugo.marvelapplication.MarvelApplication
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService.GetCharactersCallback
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.viewmodel.view.MainActivityView
import javax.inject.Inject

/**
 * @author by hugo on 5/24/17.
 */

class MainActivityViewModel : BaseViewModel<MainActivityView>() {

    val TAG = "MainActivityViewModel"

    @Inject
    lateinit var mService: MarvelApiService

    init {
        MarvelApplication.mAppComponent.inject(this)
    }

    fun getHeroes() {
        mCompositeDisposable.add(mService.getHeroesList(callback = object : GetCharactersCallback {
            override fun onNext(characters: Characters) {
                mView?.loadHeroes(characters)
            }

            override fun onError(networkError: Throwable) {
                mView?.error(networkError)
            }

            override fun onCompleted() {
                Log.i(TAG, "Loading heroes list complete successfully.")
            }
        }))
    }

}