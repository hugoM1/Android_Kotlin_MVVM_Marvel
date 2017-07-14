package com.mecalco.hugo.marvelapplication.viewmodel

import android.util.Log
import com.mecalco.hugo.marvelapplication.MarvelApplication
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics
import com.mecalco.hugo.marvelapplication.viewmodel.view.DetailActivityView
import javax.inject.Inject

/**
 * @author by hugo on 6/23/17.
 */
class DetailActivityViewModel: BaseViewModel<DetailActivityView>() {

    val TAG = "DetailActivityViewModel"

    @Inject
    lateinit var mService: MarvelApiService

    init {
        MarvelApplication.mAppComponent.inject(this)
    }

    fun getDetails(characterID : Int){
        mCompositeDisposable.add(mService.getHeroDetail(
                callback = object : MarvelApiService.GetCharactersCallback {
                    override fun onNext(characters: Characters) {
                        mView?.loadHeroDetails(characters.data?.results!![0])
                    }

                    override fun onError(networkError: Throwable) {
                        mView?.error(networkError)
                    }

                    override fun onCompleted() {
                        Log.i(TAG, "Loading heroes detail complete successfully.")
                    }
                }, characterID = characterID))
    }

    fun getComics(characterID : Int){
        mCompositeDisposable.add(mService.getComicsByCharacterId(
                callback = object : MarvelApiService.GetComicsCallBack {
                    override fun onNext(comics: Comics) {
                        mView?.loadCharacterComics(comics)
                    }

                    override fun onError(networkError: Throwable) {
                        mView?.error(networkError)
                    }

                    override fun onCompleted() {
                        Log.i(TAG, "Loading comics detail complete successfully.")
                    }

                }, characterID = characterID))
    }

}