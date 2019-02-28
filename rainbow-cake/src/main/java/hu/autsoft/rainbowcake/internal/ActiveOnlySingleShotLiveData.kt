package hu.autsoft.rainbowcake.internal

/**
 *
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
        if (hasObservers()) {
            super.setValue(t)
        }
    }

}
