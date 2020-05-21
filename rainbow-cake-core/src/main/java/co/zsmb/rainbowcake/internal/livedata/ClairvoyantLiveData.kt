package co.zsmb.rainbowcake.internal.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

/**
 * A customized LiveData implementation that can be assigned a value in a decoupled,
 * non-blocking fashion using [placeValue], and this newly assigned value is immediately
 * reflected in calls to its [getValue] function, even if they haven't been set in the
 * parent class yet, and observers have not been notified of it.
 *
 * Be wary of using [setValue] and [postValue] with this class. It works best and most
 * predictably if you stick to only using [placeValue] on it. See the tests for examples
 * of what happens if you combine it with other methods.
 */
internal class ClairvoyantLiveData<T : Any> : MutableLiveData<T>() {

    private var pendingData: T? = null

    override fun setValue(value: T?) {
        super.setValue(value)
        pendingData = null
    }

    @MainThread
    fun placeValue(value: T) {
        pendingData = value
        postValue(value)
    }

    override fun getValue(): T? {
        if (pendingData != null) {
            return pendingData
        }
        return super.getValue()
    }

}
