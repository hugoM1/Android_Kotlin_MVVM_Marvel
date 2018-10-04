package com.mecalco.hugo.marvelapplication.ui.characterdetail

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.base.BaseActivity
import com.mecalco.hugo.marvelapplication.base.VMActivity
import com.mecalco.hugo.marvelapplication.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*

@VMActivity(layoutId = R.layout.activity_detail)
class HeroDetailActivity : BaseActivity<HeroDetailActivityViewModel>(HeroDetailActivityViewModel::class) {
    private var mIntentExtras = Bundle()
    private var mHeroID: Int = 0
    var toolbar: ActionBar? = null
    private lateinit var mBinding: ActivityDetailBinding

    private lateinit var mComicAdapter: ComicsAdapter

    override fun setupViews(view: View) {
        mBinding = DataBindingUtil.setContentView<ActivityDetailBinding>(this, getLayoutId())
        mIntentExtras = intent.extras
        mHeroID = mIntentExtras.getInt(CHARACTER_ID)
        setSupportActionBar(view.toolbar)
        toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.title = mIntentExtras.getString(CHARACTER_NAME)
        mComicAdapter = ComicsAdapter(this)
        view.comicsList.adapter = mComicAdapter
        view.empty.visibility = View.VISIBLE
        viewModel.getDetails(mHeroID)
        viewModel.getComics(mHeroID)
    }

    override fun subscribeToViewModel(viewModel: HeroDetailActivityViewModel) {
        viewModel.heroDetail.observe(this, Observer { heroDetail ->
            mBinding.heroDetail = heroDetail
            if (heroDetail?.description!!.isNotBlank()) {
                heroDetailText.text = heroDetail.description
            } else {
                heroDetailText.text = "No Description"
            }

        })

        viewModel.comicsByCharacter.observe(this, Observer { comics ->
            empty.visibility = View.INVISIBLE
            mComicAdapter.mComics = comics?.data?.results!!

        })

    }

    companion object {
        const val CHARACTER_ID = "characterID"
        const val CHARACTER_NAME = "characterName"

    }

}
