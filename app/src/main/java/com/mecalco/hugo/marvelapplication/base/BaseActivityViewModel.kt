package com.mecalco.hugo.marvelapplication.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.mecalco.hugo.marvelapplication.AppExecutors
import com.mecalco.hugo.marvelapplication.MarvelApplication
import org.koin.standalone.KoinComponent
import java.lang.ref.WeakReference


abstract class BaseActivityViewModel(appContext: Context, private  val executors: AppExecutors):ViewModel(),LifecycleObserver,KoinComponent {

    // Prevents field leaks context object warning
    private val weakContext: WeakReference<Context> = WeakReference(appContext)

    internal fun addObserver(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    internal fun removeObserver(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    internal fun executors(): AppExecutors {
        return executors
    }

    /**
     * This method provides application context that can be used in ViewModel
     * Caution: A ViewModel must NEVER reference a VIEW, LIFECYCLE, or any class that may hold a reference to the activity CONTEXT.
     * @return application context
     */
    fun applicationContext(): Context {
        weakContext.get()?.let {
            return it
        }
        return MarvelApplication.context
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onViewAttached() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onViewDetached() {}

}