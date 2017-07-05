package com.mecalco.hugo.marvelapplication.ui.activities

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import com.mecalco.hugo.marvelapplication.viewmodel.BaseViewModel
import com.mecalco.hugo.marvelapplication.viewmodel.view.IView

/**
 * @author by hugo on 5/25/17.
 */

abstract class BaseActivity<B : ViewDataBinding, T : BaseViewModel<*>> : AppCompatActivity(), IView {

    lateinit var mViewModel: T
    lateinit var mBinding: B

    fun bindView(layout: Int) {
//        if (mViewModel == null) {
//            throw IllegalStateException("viewModel must not be null and should be injected")
//        }
        mBinding = DataBindingUtil.setContentView(this, layout)
    }

    override fun onStop() {
        super.onStop()
        mViewModel.clearSubscriptions()

    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.detach()
    }

}