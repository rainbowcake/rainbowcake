package co.zsmb.rainbowcake.navigation

import android.support.v4.app.Fragment

/**
 * An extension of [Navigator] to provide extra functionality for the
 * Activity in charge of navigation.
 */
interface ExtendedNavigator : Navigator {

    /**
     * Returns the Fragment currently on top of the stack, or null if there is no Fragment
     * on the stack yet.
     */
    fun getTopFragment(): Fragment?

    /**
     * Performs a press of the back button. This may be handled by a [BackPressAware]
     * Fragment, the FragmentManager, or if there is no back navigation to perform,
     * it will close the application.
     */
    fun onBackPressed()

}
