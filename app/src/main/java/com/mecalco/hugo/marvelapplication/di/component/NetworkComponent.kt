package com.mecalco.hugo.marvelapplication.di.component

import com.mecalco.hugo.marvelapplication.di.module.NetworkModule
import com.mecalco.hugo.marvelapplication.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * @author by hugo on 5/25/17.
 */
@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface NetworkComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)

}