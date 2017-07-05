package com.mecalco.hugo.marvelapplication.api.services

import com.mecalco.hugo.marvelapplication.BuildConfig
import com.mecalco.hugo.marvelapplication.api.MarvelApi
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author by hugo on 5/24/17.
 */
 class MarvelApiService(val mMarvelApi: MarvelApi) {

    fun getHeroesList(callback: GetCharactersListCallback): Disposable {
        return mMarvelApi.getCharactersList(BuildConfig.PUBLIC_KEY, BuildConfig.LIMIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ characters: Characters ->
                    if (characters.data != null) {
                        callback.onNext(characters)
                        callback.onCompleted()
                    } else {
                        callback.onError(Throwable("Something went wrong getting Heroes"))
                    }
                })
    }

    fun getHeroDetail(callback: GetCharactersListCallback, characterID: Int): Disposable {
        return mMarvelApi.getCharacterDetail(characterID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ character: Characters ->
                    if (character.data != null) {
                        callback.onNext(character)
                        callback.onCompleted()
                    } else {
                        callback.onError(Throwable("Something went wrong getting Hero Detail"))
                    }
                })
    }

    fun getComicsByCharacterId(callback: GetComicsByCharacterCallBack, characterID: Int) : Disposable{
        return mMarvelApi.getComicsByCharacter(characterID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({comics: Comics ->
                    if(comics.data != null){
                        callback.onNext(comics)
                        callback.onCompleted()
                    }else{
                        callback.onError(Throwable("Something went wrong getting Comics"))
                    }
                })
    }

    interface GetCharactersListCallback {
        fun onNext(characters: Characters)
        fun onError(networkError: Throwable)
        fun onCompleted()
    }

    interface GetComicsByCharacterCallBack{
        fun onNext(comics: Comics)
        fun onError(networkError: Throwable)
        fun onCompleted()
    }

}