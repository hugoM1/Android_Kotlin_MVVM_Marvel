package com.mecalco.hugo.marvelapplication


import java.util.concurrent.Executor

/**
 * Global executor pools for the whole application.
 */
class AppExecutors( val diskIO: Executor,  val networkIO: Executor,  val main: Executor) {

    fun diskIO():Executor {
        return diskIO
    }

    fun networkIO():Executor{
        return networkIO
    }

    fun ui():Executor{
        return main
    }

}