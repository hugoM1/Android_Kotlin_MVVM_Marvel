package com.mecalco.hugo.marvelapplication


import java.util.concurrent.Executor

/**
 * Global executor pools for the whole application.
 */
class AppExecutors(private val diskIO: Executor, private val networkIO: Executor, private val main: Executor) {

    fun disk():Executor {
        return diskIO
    }

    fun network():Executor{
        return networkIO
    }

    fun ui():Executor{
        return main
    }

}