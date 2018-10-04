package com.mecalco.hugo.marvelapplication.ui.characters

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.mecalco.hugo.marvelapplication.AppExecutors
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService.GetCharactersCallback
import com.mecalco.hugo.marvelapplication.base.BaseActivityViewModel
import com.mecalco.hugo.marvelapplication.model.Characters
import io.reactivex.disposables.CompositeDisposable

/**
 * @author by hugo on 5/24/17.
 */

class HeroesListActivityViewModel(appContext: Context, appExecutors: AppExecutors, private val marvelApiService: MarvelApiService) : BaseActivityViewModel(appContext, appExecutors) {

    var heroList = MutableLiveData<Characters>()
    var error = MutableLiveData<Throwable>()


    fun getHeroes() {
        CompositeDisposable().add(marvelApiService.getHeroesList(callback = object : GetCharactersCallback {
            override fun onNext(characters: Characters) {
                heroList.postValue(characters)
            }

            override fun onError(networkError: Throwable) {
                error.postValue(networkError)
            }

            override fun onCompleted() {
                Log.i(TAG, "Loading heroes list complete successfully.")
            }
        }))
    }

    companion object {
        val TAG = HeroesListActivityViewModel::class.java.toString()
    }

}