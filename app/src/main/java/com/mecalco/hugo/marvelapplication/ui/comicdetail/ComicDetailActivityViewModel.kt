package com.mecalco.hugo.marvelapplication.ui.comicdetail

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.mecalco.hugo.marvelapplication.AppExecutors
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import com.mecalco.hugo.marvelapplication.base.BaseActivityViewModel
import com.mecalco.hugo.marvelapplication.model.Comics
import io.reactivex.disposables.CompositeDisposable

/**
 * @author by hugo on 7/13/17.
 */
class ComicDetailActivityViewModel(appContext: Context, appExecutors: AppExecutors, private val marvelApiService: MarvelApiService) : BaseActivityViewModel(appContext, appExecutors) {

    var comicDetails = MutableLiveData<Comics>()
    var error = MutableLiveData<Throwable>()
    var mCompositeDisposable = CompositeDisposable()


    fun getDetails(comicID: Int) {
        mCompositeDisposable.add(marvelApiService.getComicDetail(
                callback = object : MarvelApiService.GetComicsCallBack {
                    override fun onNext(comics: Comics) {
                        comicDetails.postValue(comics)
                    }

                    override fun onCompleted() {
                        Log.i(TAG, "Loading comic detail complete successfully.")
                    }

                    override fun onError(networkError: Throwable) {
                        error.postValue(networkError)
                    }
                }, comicID = comicID))
    }

    companion object {
        val TAG = ComicDetailActivityViewModel::class.java.toString()
    }

}