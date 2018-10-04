package com.mecalco.hugo.marvelapplication.ui.characterdetail

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.mecalco.hugo.marvelapplication.AppExecutors
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import com.mecalco.hugo.marvelapplication.base.BaseActivityViewModel
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics
import io.reactivex.disposables.CompositeDisposable

/**
 * @author by hugo on 6/23/17.
 */
class HeroDetailActivityViewModel(appContext: Context, appExecutors: AppExecutors, private val marvelApiService: MarvelApiService) : BaseActivityViewModel(appContext, appExecutors) {

    var heroDetail = MutableLiveData<Characters.DataBean.ResultsBean>()
    var comicsByCharacter = MutableLiveData<Comics>()
    var error = MutableLiveData<Throwable>()

    private var mCompositeDisposable = CompositeDisposable()

    fun getDetails(characterID: Int) {
        CompositeDisposable().add(marvelApiService.getHeroDetail(
                callback = object : MarvelApiService.GetCharactersCallback {
                    override fun onNext(characters: Characters) {
                        heroDetail.postValue(characters.data?.results!![0])
                    }

                    override fun onError(networkError: Throwable) {
                        error.postValue(networkError)
                    }

                    override fun onCompleted() {
                        Log.i(TAG, "Loading heroes detail complete successfully.")
                    }
                }, characterID = characterID))
    }

    fun getComics(characterID: Int) {
        mCompositeDisposable.add(marvelApiService.getComicsByCharacterId(
                callback = object : MarvelApiService.GetComicsCallBack {
                    override fun onNext(comics: Comics) {
                        comicsByCharacter.postValue(comics)
                    }

                    override fun onError(networkError: Throwable) {
                        error.postValue(networkError)
                    }

                    override fun onCompleted() {
                        Log.i(TAG, "Loading comics detail complete successfully.")
                    }

                }, characterID = characterID))
    }


    companion object {
        val TAG = HeroDetailActivityViewModel::class.java.toString()
    }

}