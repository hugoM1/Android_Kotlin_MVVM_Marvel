package com.mecalco.hugo.marvelapplication.ui.activities

import android.os.Bundle
import android.support.v7.app.ActionBar
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.databinding.ActivityComicDetailBinding
import com.mecalco.hugo.marvelapplication.model.Comics
import com.mecalco.hugo.marvelapplication.viewmodel.ComicDetailActivityViewModel
import com.mecalco.hugo.marvelapplication.viewmodel.view.ComicDetailActivityView

class ComicDetailActivity : BaseActivity<ActivityComicDetailBinding, ComicDetailActivityViewModel>(),
        ComicDetailActivityView {

    var toolbar: ActionBar? = null
    var mIntentExtras = Bundle()
    var mComicId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ComicDetailActivityViewModel()
        mViewModel.attach(this)

        bindView(R.layout.activity_comic_detail)

        mIntentExtras = intent.extras

        mComicId = mIntentExtras.getInt("comicID")

        mViewModel.getDetails(mComicId)

        mBinding.comicDetail = Comics.DataBean.ResultsBean()

        setSupportActionBar(mBinding.toolbar)

        toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.title = mIntentExtras.getString("comicTitle")

    }

    override fun loadComicDetails(comics: Comics) {
        var comic = comics.data?.results?.get(0)
        mBinding.comicDetail = comic
        mBinding.description.text = comic?.description
    }


    override fun error(t: Throwable) {
    }

    override fun error() {
    }

}
