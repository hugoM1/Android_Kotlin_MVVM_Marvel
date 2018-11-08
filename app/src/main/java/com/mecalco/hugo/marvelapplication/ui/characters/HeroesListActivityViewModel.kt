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
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author by hugo on 5/24/17.
 */

class HeroesListActivityViewModel(appContext: Context, appExecutors: AppExecutors, private val marvelApiService: MarvelApiService) : BaseActivityViewModel(appContext, appExecutors) {

    var heroList = MutableLiveData<List<Characters.DataBean.ResultsBean>>()
    var error = MutableLiveData<Throwable>()
    var sortByDate = MutableLiveData<Boolean>()
    var currentList = ArrayList<Characters.DataBean.ResultsBean>()


    fun getHeroes() {
        CompositeDisposable().add(marvelApiService.getHeroesList(callback = object : GetCharactersCallback {
            override fun onNext(characters: Characters) {
                sortByDate.postValue(true)
                currentList = characters.data?.results as ArrayList<Characters.DataBean.ResultsBean>
                heroList.postValue(currentList)
            }

            override fun onError(networkError: Throwable) {
                error.postValue(networkError)
            }

            override fun onCompleted() {
                Log.i(TAG, "Loading heroes list complete successfully.")
            }
        }))
    }

    fun getHeroesByName() {
        val characters = heroList.value;
        val items = ArrayList<Characters.DataBean.ResultsBean>()
        items += (characters as ArrayList)
        sortByName(items)

        heroList.postValue(items)
    }

    fun getHerosByDate() {
        if (currentList.isNotEmpty()) {
            heroList.postValue(currentList)
        }
    }

    fun addItem() {
        val characters = heroList.value;
        val items = ArrayList<Characters.DataBean.ResultsBean>()
        items += (characters as ArrayList)
        items.add(1, Characters.DataBean.ResultsBean(0, "Not Defined", "", "", null))
        heroList.postValue(items)
    }

    fun deletItem() {
        val characterList = heroList.value as? ArrayList<Characters.DataBean.ResultsBean>
        val items = ArrayList<Characters.DataBean.ResultsBean>()
        items += (characterList as ArrayList)
        items.removeAt(1)
        heroList.postValue(items)
    }

    private fun sortByName(items: ArrayList<Characters.DataBean.ResultsBean>) {
        Collections.sort(items) { lhs, rhs ->
            var l = Character.toUpperCase(lhs.name!!.toCharArray()[0])

            if (l < 'A' || l > 'Z')

                l += 'Z'.toInt()

            var r = Character.toUpperCase(rhs.name!!.toCharArray()[0])

            if (r < 'A' || r > 'Z') {
                r += 'Z'.toInt()
            }

            val s1 = l + lhs.name!!.substring(1)

            val s2 = r + rhs.name!!.substring(1)

            s1.compareTo(s2)
        }
    }


    companion object {
        val TAG = HeroesListActivityViewModel::class.java.toString()
    }


}