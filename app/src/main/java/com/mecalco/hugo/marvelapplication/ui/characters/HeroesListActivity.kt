package com.mecalco.hugo.marvelapplication.ui.characters

import android.arch.lifecycle.Observer
import android.util.Log
import android.view.View
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.base.BaseActivity
import com.mecalco.hugo.marvelapplication.base.VMActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


@VMActivity(layoutId = R.layout.activity_main)
class HeroesListActivity : BaseActivity<HeroesListActivityViewModel>(HeroesListActivityViewModel::class) {


    lateinit var mHeroesListAdapter: HeroesListAdapter

    override fun setupViews(view: View) {
        setSupportActionBar(view.toolbar)
        mHeroesListAdapter = HeroesListAdapter(this)
        view.photosList.adapter = mHeroesListAdapter
        empty.visibility = View.VISIBLE
        viewModel.getHeroes()
    }

    override fun subscribeToViewModel(viewModel: HeroesListActivityViewModel) {

        viewModel.heroList.observe(this, Observer { characters ->
            empty.visibility = View.INVISIBLE
            mHeroesListAdapter.mCharacters = characters?.data?.results!!
        })

        viewModel.error.observe(this, Observer { throwable ->
            Log.e(TAG, throwable?.message)
        })

    }

    companion object {
        const val TAG = "HeroesListActivity"
    }

}
