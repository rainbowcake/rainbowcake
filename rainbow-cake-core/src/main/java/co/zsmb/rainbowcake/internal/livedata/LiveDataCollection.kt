package co.zsmb.rainbowcake.internal.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * A dynamic collection of [LiveData] instances. Each observer registered
 * will observe its own unique [LiveData] instance.
 */
internal interface LiveDataCollection<T : Any> {

    /**
     * Adds the given observer to the observers list within the lifespan of the given
     * owner. See [observe][LiveData.observe].
     */
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

}
