package hu.autsoft.rainbowcake.internal

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.OnLifecycleEvent

/**
 *
 */
internal class MutableLiveDataCollectionImpl<T : Any>(
        private val factory: () -> MutableLiveData<T>
) : MutableLiveDataCollection<T> {

    /**
     *
     */
    internal val activeLiveData: MutableSet<MutableLiveData<T>> = mutableSetOf()

    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        val liveData = factory()
        attachRemover(owner, liveData)
        liveData.observe(owner, observer)
        activeLiveData += liveData
    }

    override fun setValue(value: T?) {
        activeLiveData.forEach { it.setValue(value) }
    }

    override fun postValue(value: T?) {
        activeLiveData.forEach { it.postValue(value) }
    }

    /**
     *
     */
    private fun removeLiveData(liveData: LiveData<T>) {
        activeLiveData.remove(liveData)
    }

    /**
     *
     */
    private fun attachRemover(owner: LifecycleOwner, liveData: MutableLiveData<T>) {
        val watcher = LiveDataRemover(liveData)
        owner.lifecycle.addObserver(watcher)
    }

    /**
     *
     */
    private inner class LiveDataRemover(private val liveData: MutableLiveData<T>) : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            removeLiveData(liveData)
        }
    }

}
