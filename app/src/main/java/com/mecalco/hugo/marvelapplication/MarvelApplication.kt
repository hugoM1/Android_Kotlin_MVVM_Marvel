package com.mecalco.hugo.marvelapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.mecalco.hugo.marvelapplication.di.appModule

import org.koin.android.ext.android.startKoin
import java.io.File

/**
 * @author by hugo on 5/24/17.
 */
class MarvelApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // initialize dependency injection
        val cacheFile = File(cacheDir, "heroesCache")
        startKoin(this, appModule)

    }



}