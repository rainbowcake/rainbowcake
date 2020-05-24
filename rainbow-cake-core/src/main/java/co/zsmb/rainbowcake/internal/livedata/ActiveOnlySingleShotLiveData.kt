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

    companion object {
        private const val NO_BACKGROUND = "ActiveOnlySingleShotLiveData can not receive values from background threads"
    }

    @Deprecated(
            message = NO_BACKGROUND,
            replaceWith = ReplaceWith("setValue(value)"),
            level = DeprecationLevel.ERROR
    )
    override fun postValue(value: T?) {
        throw UnsupportedOperationException(NO_BACKGROUND)
    }

    override fun setValue(t: T?) {
        if (hasActiveObservers()) {
            super.setValue(t)
        }
    }

}
