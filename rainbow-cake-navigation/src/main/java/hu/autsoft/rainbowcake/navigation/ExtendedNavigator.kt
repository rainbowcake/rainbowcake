package hu.autsoft.rainbowcake.navigation

import android.support.v4.app.Fragment

/**
 * An extension of [Navigator] to provide extra functionality for the
 * Activity in charge of navigation.
 */
interface ExtendedNavigator : Navigator {

    fun getTopFragment(): Fragment?

    fun onBackPressed()

}
