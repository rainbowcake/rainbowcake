package hu.autsoft.rainbowcake.internal.livedata

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.OnLifecycleEvent

/**
 * An implementation of [MutableLiveDataCollection] which can use
 * any [MutableLiveData] implementation internally for its contained
 * [LiveData] instances.
 *
 * @property factory the factory method used to create a new [MutableLiveData]
 *                   if required by a new observer being added.
 */
internal class MutableLiveDataCollectionImpl<T : Any>(
        private val factory: () -> MutableLiveData<T>
) : MutableLiveDataCollection<T> {

    /**
     * The set of currently contained [LiveData] instances. Instances
     * that are no longer observed are removed immediately, see [LiveDataRemover].
     *
     * Internal visibility only for testing.
     */
    internal val activeLiveData: MutableSet<MutableLiveData<T>> = mutableSetOf()

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        val liveData = factory()
        activeLiveData += liveData
        attachRemover(owner, liveData)
        liveData.observe(owner, observer)
    }

    override fun setValue(value: T?) {
        activeLiveData.forEach { it.setValue(value) }
    }

    override fun postValue(value: T?) {
        activeLiveData.forEach { it.postValue(value) }
    }

    /**
     * Removes a given [LiveData] instance from the set of stored instances.
     */
    private fun removeLiveData(liveData: LiveData<T>) {
        activeLiveData.remove(liveData)
    }

    /**
     * Creates a [LiveDataRemover] which will remove the given [liveData] from
     * the set of contained instances when the [owner]'s lifecycle is destroyed.
     */
    private fun attachRemover(owner: LifecycleOwner, liveData: MutableLiveData<T>) {
        val watcher = LiveDataRemover(liveData)
        owner.lifecycle.addObserver(watcher)
    }

    /**
     * A [LifecycleObserver] that removes a given [liveData] from the collection
     * when the lifecycle it observes is destroyed.
     */
    private inner class LiveDataRemover(private val liveData: MutableLiveData<T>) : LifecycleObserver {
        /**
         * Removes the [liveData] instance from the collection.
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            removeLiveData(liveData)
        }
    }

}
