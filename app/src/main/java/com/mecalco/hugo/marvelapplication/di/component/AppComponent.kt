package com.mecalco.hugo.marvelapplication.di.component

import com.mecalco.hugo.marvelapplication.di.module.AppModule
import com.mecalco.hugo.marvelapplication.di.module.NetworkModule
import com.mecalco.hugo.marvelapplication.ui.activities.MainActivity
import com.mecalco.hugo.marvelapplication.viewmodel.DetailActivityViewModel
import com.mecalco.hugo.marvelapplication.viewmodel.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * @author by hugo on 5/24/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(detailAcivityViewModel: DetailActivityViewModel)
}