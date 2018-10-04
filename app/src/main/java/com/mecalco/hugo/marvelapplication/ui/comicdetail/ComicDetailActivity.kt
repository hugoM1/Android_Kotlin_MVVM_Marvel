package com.mecalco.hugo.marvelapplication.ui.comicdetail

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.base.BaseActivity
import com.mecalco.hugo.marvelapplication.base.VMActivity
import com.mecalco.hugo.marvelapplication.databinding.ActivityComicDetailBinding
import com.mecalco.hugo.marvelapplication.model.Comics
import kotlinx.android.synthetic.main.activity_comic_detail.*
import kotlinx.android.synthetic.main.activity_comic_detail.view.*

@VMActivity(layoutId = R.layout.activity_comic_detail)
class ComicDetailActivity : BaseActivity<ComicDetailActivityViewModel>(ComicDetailActivityViewModel::class) {


    var toolbar: ActionBar? = null
    var mIntentExtras = Bundle()
    var mComicId: Int = 0
    lateinit var mBinding: ActivityComicDetailBinding


    override fun setupViews(view: View) {
        mIntentExtras = intent.extras
        mComicId = mIntentExtras.getInt("comicID")
        viewModel.getDetails(mComicId)
        mBinding = DataBindingUtil.setContentView<ActivityComicDetailBinding>(this, getLayoutId())
        mBinding.comicDetail = Comics.DataBean.ResultsBean()
        setSupportActionBar(view.toolbar)
        toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.title = mIntentExtras.getString("comicTitle")
    }

    override fun subscribeToViewModel(viewModel: ComicDetailActivityViewModel) {
        viewModel.comicDetails.observe(this, Observer { comics ->
            var comic = comics?.data?.results?.get(0)
            mBinding.comicDetail = comic
            description.text = comic?.description

        })
    }


}
