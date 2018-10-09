package hu.autsoft.rainbowcake.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import hu.autsoft.rainbowcake.R
import kotlin.reflect.KClass


/**
 * A default implementation of [Navigator] with mostly straightforward
 * [FragmentManager] actions and default transition animations.
 */
internal class NavigatorImpl(private val activity: AppCompatActivity) : ExtendedNavigator {

    private val supportFragmentManager = activity.supportFragmentManager

    override fun add(fragment: Fragment) {
        if (supportFragmentManager.isStateSaved) {
            return
        }

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.default_transition_in, R.anim.default_transition_out, R.anim.default_transition_in, R.anim.default_transition_out)
                .replace(R.id.contentFrame, fragment, fragment.navTag)
                .addToBackStack(fragment.navTag)
                .commit()
    }

    override fun replace(fragment: Fragment) {
        if (supportFragmentManager.isStateSaved) {
            return
        }

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.default_transition_in, R.anim.default_transition_out, R.anim.default_transition_in, R.anim.default_transition_out)
                .replace(R.id.contentFrame, fragment, fragment.navTag)
                .commit()
    }

    override fun pop(): Boolean {
        if (supportFragmentManager.isStateSaved) {
            return false
        }

        return supportFragmentManager.popBackStackImmediate()
    }

    override fun popUntil(fragmentKClass: KClass<out Fragment>): Boolean {
        if (supportFragmentManager.isStateSaved) {
            return false
        }

        return supportFragmentManager.popBackStackImmediate(fragmentKClass.navTag, 0)
    }

    override fun setStack(vararg fragments: Fragment) {
        if (supportFragmentManager.isStateSaved) {
            return
        }

        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragments.forEach(this::add)
    }

    override fun closeApplication() {
        activity.finish()
    }

    override fun onBackPressed() {
        val fragment = getTopFragment()
        val handledBackPress = fragment is BackPressAware && fragment.onBackPressed()
        if (!handledBackPress) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                pop()
            } else {
                activity.finish()
            }
        }
    }

    override fun getTopFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.contentFrame)
    }

}
