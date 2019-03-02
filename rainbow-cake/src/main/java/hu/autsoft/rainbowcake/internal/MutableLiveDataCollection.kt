package hu.autsoft.rainbowcake.internal

import android.arch.lifecycle.LiveData

/**
 * A dynamic, mutable collection of [LiveData] instances. Each observer
 * registered will observe its own unique [LiveData] instance. Setting or
 * posting a value to the collection will forward that value to all contained
 * [LiveData] instances.
 */
internal interface MutableLiveDataCollection<T : Any> : LiveDataCollection<T> {

    /**
     * Sets a new value for all contained [LiveData] instances. Must
     * be called from the main thread. See [setValue][LiveData.setValue].
     */
    fun setValue(value: T?)

    /**
     * Sets a new value for all contained [LiveData] instances. May be called
     * from a background thread. See [postValue][LiveData.postValue].
     */
    fun postValue(value: T?)

}
