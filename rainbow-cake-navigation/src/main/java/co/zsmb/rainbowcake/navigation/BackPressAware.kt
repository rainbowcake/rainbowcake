package co.zsmb.rainbowcake.navigation

/**
 * An interface that enables individual Fragments to get notified of and
 * optionally handle presses of the back button.
 */
interface BackPressAware {

    /**
     * Called when the hardware back button is pressed.
     *
     * @return true if the back press event was consumed by the Fragment.
     */
    fun onBackPressed(): Boolean

}
