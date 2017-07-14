package com.mecalco.hugo.marvelapplication.viewmodel

import android.util.Log
import com.mecalco.hugo.marvelapplication.MarvelApplication
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import com.mecalco.hugo.marvelapplication.model.Comics
import com.mecalco.hugo.marvelapplication.viewmodel.view.ComicDetailActivityView
import javax.inject.Inject

/**
 * @author by hugo on 7/13/17.
 */
class ComicDetailActivityViewModel: BaseViewModel<ComicDetailActivityView>() {

    val TAG = ComicDetailActivityViewModel::class.java.canonicalName!!

    @Inject
    lateinit var mService: MarvelApiService

    init {
        MarvelApplication.mAppComponent.inject(this)
    }

    fun getDetails(comicID: Int){
        mCompositeDisposable.add(mService.getComicDetail(
                callback = object :MarvelApiService.GetComicsCallBack{
            override fun onNext(comics: Comics) {
                mView?.loadComicDetails(comics)
            }

            override fun onCompleted() {
                Log.i(TAG, "Loading comic detail complete successfully.")
            }

            override fun onError(networkError: Throwable) {
                mView?.error(networkError)
            }
        }, comicID = comicID))
    }

}