package hu.autsoft.rainbowcake.internal

/**
 *
 */
internal interface MutableLiveDataCollection<T : Any> : LiveDataCollection<T> {

    /**
     *
     */
    fun setValue(value: T?)

    /**
     *
     */
    fun postValue(value: T?)

}
