package hu.autsoft.rainbowcake.navigation

import android.support.v4.app.Fragment
import kotlin.reflect.KClass


/**
 * A basic set of Fragment-based navigation actions.
 */
interface Navigator {

    /**
     * Adds [fragment] to the top of the current Fragment stack.
     */
    fun add(fragment: Fragment)

    /**
     * Replaces the top Fragment on the stack with [fragment].
     */
    fun replace(fragment: Fragment)

    /**
     * Removes the top Fragment from the stack.
     */
    fun pop(): Boolean

    /**
     * Removes Fragments from the top of the stack until the type of Fragment specified,
     * not including said Fragment. If the Fragment is not found, no changes are made.
     */
    fun popUntil(fragmentKClass: KClass<out Fragment>): Boolean

    /**
     * Clears the entire Fragment backstack, and adds the [fragments] to it, in the
     * order they're passed to this function (last on top of the stack).
     */
    fun setStack(vararg fragments: Fragment)

    /**
     * Finishes the current Activity.
     */
    fun closeApplication()

}

/**
 * Fetches the [Navigator] instance from the Activity containing the Fragment.
 *
 * @throws IllegalStateException if the Fragment is in an Activity that doesn't have a [Navigator]
 */
val Fragment.navigator: Navigator?
    get() {
        val activity = activity ?: return null
        return (activity as? NavActivity<*, *>)
                ?.navigator
                ?: throw IllegalStateException("Fragment is not in an Activity that implements Navigator")
    }

/**
 * The fully qualified name of the Fragment instance, used as both the name of the
 * transition that places the Fragment on the back stack and as the Fragment's
 * tag in the FragmentManager.
 */
internal val Fragment.navTag: String
    get() {
        return this::class.qualifiedName!!
    }

/**
 * The fully qualified name of the Fragment class, used as both the name of the
 * transition that places the Fragment on the back stack and as the Fragment's
 * tag in the FragmentManager.
 */
internal val KClass<out Fragment>.navTag: String
    get() {
        return this.qualifiedName!!
    }
