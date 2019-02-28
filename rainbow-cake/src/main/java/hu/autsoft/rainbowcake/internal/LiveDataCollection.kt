package hu.autsoft.rainbowcake.internal

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

/**
 *
 */
internal interface LiveDataCollection<T : Any> {

    /**
     *
     */
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

}
