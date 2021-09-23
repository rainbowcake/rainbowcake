package co.zsmb.rainbowcake.internal.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * A dynamic collection of [LiveData] instances. Each observer registered
 * will observe its own unique [LiveData] instance.
 */
public interface LiveDataCollection<T : Any> {

    /**
     * Adds the given observer to the observers list within the lifespan of the given
     * owner. See [observe][LiveData.observe].
     */
    public fun observe(owner: LifecycleOwner, observer: Observer<T>)

    /**
     * Removes the given observer from this collection.
     */
    public fun removeObserver(observer: Observer<T>)
}
