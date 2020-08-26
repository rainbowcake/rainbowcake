package co.zsmb.rainbowcake.navigation

import androidx.fragment.app.Fragment

/**
 * An extension of [Navigator] to provide extra functionality for the
 * Activity in charge of navigation.
 */
public interface ExtendedNavigator : Navigator {

    /**
     * Returns the Fragment currently on top of the stack, or null if there is no Fragment
     * on the stack yet.
     */
    public fun getTopFragment(): Fragment?

    /**
     * Performs a press of the back button. This may be handled by a [BackPressAware]
     * Fragment, the FragmentManager, or if there is no back navigation to perform,
     * it will close the application.
     */
    public fun onBackPressed()

}
