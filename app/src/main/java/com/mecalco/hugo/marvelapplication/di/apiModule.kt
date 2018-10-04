package com.mecalco.hugo.marvelapplication.di

import android.app.Application
import android.content.ContextWrapper
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mecalco.hugo.marvelapplication.BuildConfig
import com.mecalco.hugo.marvelapplication.api.MarvelApi
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


val apiModule = applicationContext {
    bean { createRetrofit(getOkHttpClient(androidApplication())) }
    bean { getMarvelApiService(get()) }


}

private fun getOkHttpClient(context:Application): OkHttpClient {
    var cache: Cache? = null
    try {
        val cacheFile = File(context.cacheDir, "heroesCache")
        cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return OkHttpClient.Builder()
//                .addInterceptor { chain ->
//                    val original = chain.request()
//
//                    // Customize the request
//                    val request = original.newBuilder()
//                            .header("Content-Type", "application/json")
//                            .removeHeader("Pragma")
//                            .header("Cache-Control", String.format(Locale.US, "max-age=%d", BuildConfig.CACHETIME))
//                            .build()
//
//                    val response = chain.proceed(request)
//                    response.cacheResponse()
//                    // Customize or return the response
//                    response
//                }
                 .cache(cache)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
}

private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

private fun getMarvelApiService(retrofit: Retrofit): MarvelApiService {
    return MarvelApiService(retrofit.create(MarvelApi::class.java))
}