package com.mecalco.hugo.marvelapplication.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author by hugo on 5/24/17.
 */

@Module
open class AppModule(val mApplication: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }

}