package com.mecalco.hugo.marvelapplication.di

import com.mecalco.hugo.marvelapplication.ui.characterdetail.HeroDetailActivityViewModel
import com.mecalco.hugo.marvelapplication.ui.characters.HeroesListActivityViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext


val uiModule = applicationContext {
viewModel { HeroesListActivityViewModel(get(), get(), get()) }
    viewModel { HeroDetailActivityViewModel(get(), get(), get()) }

}