package com.mecalco.hugo.marvelapplication

import android.app.Application
import com.mecalco.hugo.marvelapplication.di.component.AppComponent
import com.mecalco.hugo.marvelapplication.di.component.DaggerAppComponent
import com.mecalco.hugo.marvelapplication.di.module.AppModule
import com.mecalco.hugo.marvelapplication.di.module.NetworkModule
import java.io.File

/**
 * @author by hugo on 5/24/17.
 */
class MarvelApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var mAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        // initialize
        val cacheFile = File(cacheDir, "heroesCache")
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(cacheFile))
                .build()
    }

}