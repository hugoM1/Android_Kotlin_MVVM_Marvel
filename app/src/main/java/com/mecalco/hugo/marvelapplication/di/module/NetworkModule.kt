package com.mecalco.hugo.marvelapplication.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mecalco.hugo.marvelapplication.BuildConfig
import com.mecalco.hugo.marvelapplication.api.MarvelApi
import com.mecalco.hugo.marvelapplication.api.services.MarvelApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author by hugo on 5/25/17.
 */

@Module
class NetworkModule(private val mCacheFile: File) {

    @Provides
    @Singleton
    internal fun provideCall(): Retrofit {
        var cache: Cache? = null
        try {
            cache = Cache(mCacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val okHttpClient = OkHttpClient.Builder()
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

        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }

    @Provides
    @Singleton
    fun providesService(marvelApi: MarvelApi): MarvelApiService {
        return MarvelApiService(marvelApi)
    }

}
