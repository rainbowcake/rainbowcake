package hu.autsoft.rainbowcake.extensions.internal

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData

/**
 * Filters the events of a [LiveData] instance and returns a new one that
 * only emits distinct elements. In other words, subsequent duplicates
 * from the original will not be present in the returned [LiveData].
 *
 * @receiver The [LiveData] to filter
 * @return The new, filtered [LiveData]
 */
internal fun <T> LiveData<T>.distinct(): LiveData<T> {
    return MediatorLiveData<T>().apply {
        addSource(this@distinct) { newValue ->
            if (newValue != value) {
                value = newValue
            }
        }
    }
}
