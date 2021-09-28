package co.zsmb.rainbowcake.internal.livedata

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent

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
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val activeLiveData: MutableMap<Observer<T>, MutableLiveData<T>> = mutableMapOf()

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        val liveData = factory()
        activeLiveData[observer] = liveData
        attachRemover(owner, observer)
        liveData.observe(owner, observer)
    }

    override fun removeObserver(observer: Observer<T>) {
        removeLiveData(observer)
    }

    override fun setValue(value: T?) {
        activeLiveData.forEach { (_, livedata) -> livedata.setValue(value) }
    }

    override fun postValue(value: T?) {
        activeLiveData.forEach { (_, livedata) -> livedata.postValue(value) }
    }

    /**
     * Removes the [LiveData] instance belonging to the [observer] from the set of
     * stored instances.
     */
    private fun removeLiveData(observer: Observer<T>) {
        activeLiveData.remove(key = observer)
    }

    /**
     * Creates a [LiveDataRemover] which will remove the given [observer]'s LiveData
     * from the set of contained instances when the [owner]'s lifecycle is destroyed.
     */
    private fun attachRemover(owner: LifecycleOwner, observer: Observer<T>) {
        val watcher = LiveDataRemover(observer)
        owner.lifecycle.addObserver(watcher)
    }

    /**
     * A [LifecycleObserver] that removes the LiveData belonging to the [observer]
     * from the collection when the lifecycle it observes in is destroyed.
     */
    private inner class LiveDataRemover(private val observer: Observer<T>) : LifecycleObserver {
        /**
         * Removes the [observer] instance from the collection.
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            removeLiveData(observer)
        }
    }

}
