package com.mecalco.hugo.marvelapplication.ui.activities

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mecalco.hugo.marvelapplication.MarvelApplication
import com.mecalco.hugo.marvelapplication.R
import com.mecalco.hugo.marvelapplication.databinding.ActivityMainBinding
import com.mecalco.hugo.marvelapplication.model.Characters
import com.mecalco.hugo.marvelapplication.ui.adapters.MainAdapter
import com.mecalco.hugo.marvelapplication.viewmodel.MainActivityViewModel
import com.mecalco.hugo.marvelapplication.viewmodel.view.MainActivityView
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(), MainActivityView {

    val TAG: String = "MainActivity"

    @Inject
    lateinit var mApplication: Application

    lateinit var mMainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MarvelApplication.mAppComponent.inject(this)

        mViewModel = MainActivityViewModel()
        mViewModel.attach(this)

        bindView(R.layout.activity_main)

        val toolbar = mBinding.toolbar
        setSupportActionBar(toolbar)

        mMainAdapter = MainAdapter(this)
        mBinding.photosList.adapter = mMainAdapter

        mBinding.empty.visibility = View.VISIBLE

        mViewModel.getHeroes()
    }

    override fun error(t: Throwable) {
        Log.wtf(TAG, t)
    }

    override fun error() {
        Log.e(TAG, "Something went wrong!")
    }

    override fun loadHeroes(characters: Characters) {
        mBinding.empty.visibility = View.INVISIBLE
       // mMainAdapter.setData(characters.data?.results!!)
        mMainAdapter.mCharacters = characters.data?.results!!
    }

}
