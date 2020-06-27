package co.zsmb.rainbowcake.internal.livedata

import androidx.lifecycle.MutableLiveData

/**
 * A [MutableLiveData] implementation that may only be used from the main thread,
 * and only delivers the value set to its observer if it's currently active.
 *
 * Subclass of [SingleShotLiveData], therefore it may only have a single
 * observer and delivers the value set in it only - at most - once.
 */
internal class ActiveOnlySingleShotLiveData<T : Any> : SingleShotLiveData<T>() {

    override fun postValue(value: T?) {
        MainThreadWrapper.executor.execute { setValue(value) }
    }

    override fun setValue(t: T?) {
        if (hasActiveObservers()) {
            super.setValue(t)
        }
    }

}
