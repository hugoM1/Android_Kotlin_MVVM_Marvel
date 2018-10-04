package com.mecalco.hugo.marvelapplication.di


import android.os.Handler
import android.os.Looper
import com.mecalco.hugo.marvelapplication.AppExecutors
import org.koin.dsl.module.applicationContext
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val executorModule = applicationContext {

    bean { AppExecutors(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(ExecutorProperties.NETWORK_IO_POOL_SIZE), getMainThreadExecutor()) }

}

private fun getMainThreadExecutor(): Executor {
    return object: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}

private object ExecutorProperties {
    const val NETWORK_IO_POOL_SIZE = 3
}