package com.mecalco.hugo.marvelapplication.ui.activities

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.View
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.databinding.ActivityDetailBinding
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.model.Comics
import com.mecalco.hugo.marvelapplication.ui.adapters.ComicsAdapter
import com.mecalco.hugo.marvelapplication.viewmodel.DetailActivityViewModel
import com.mecalco.hugo.marvelapplication.viewmodel.view.DetailActivityView

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailActivityViewModel>(), DetailActivityView {

    val TAG: String = "DetailActivity"

    var mIntentExtras = Bundle()
    var mHeroID: Int = 0
    var toolbar: ActionBar? = null

    lateinit var mComicAdapter: ComicsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = DetailActivityViewModel()
        mViewModel.attach(this)

        bindView(R.layout.activity_detail)

        mIntentExtras = intent.extras

        mHeroID = mIntentExtras.getInt("characterID")

        mBinding.heroDetail = Characters.DataBean.ResultsBean()
        setSupportActionBar(mBinding.toolbar)

        toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.title = mIntentExtras.getString("characterName")

        mComicAdapter = ComicsAdapter(this)
        mBinding.comicsList.adapter = mComicAdapter

        mBinding.empty.visibility = View.VISIBLE

        mViewModel.getDetails(mHeroID)
        mViewModel.getComics(mHeroID)

    }

    override fun loadHeroDetails(character: Characters.DataBean.ResultsBean) {
        mBinding.heroDetail = character
        if (character.description!!.isNotBlank()) {
            mBinding.heroDetailText.text = character.description
        } else {
            mBinding.heroDetailText.text = "No Description"
        }
    }

    override fun loadCharacterComics(comics: Comics) {
        mBinding.empty.visibility = View.INVISIBLE
        mComicAdapter.mComics = comics.data?.results!!
    }

    override fun error(t: Throwable) {
        Log.wtf(TAG, t)
    }

    override fun error() {
        Log.e(TAG, "Something went wrong getting Hero detail!")
    }

}
