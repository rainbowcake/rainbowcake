package hu.autsoft.rainbowcake.navigation

import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes
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
     * Adds [fragment] to the top of the current Fragment stack. All four
     * animation parameters must be provided, with a 0 value indicating if
     * no animation should take place.
     *
     * @param fragment The new Fragment that will replace the _current_ one.
     * @param enterAnim The enter animation used for the view of the [fragment]
     *                  that's being added.
     * @param exitAnim The exit animation used for the view of the _current_
     *                 Fragment that's being replaced (covered).
     * @param popEnterAnim The enter animation for the view of the _current_
     *                     Fragment when it reappears due to [fragment] being
     *                     popped.
     * @param popExitAnim The exit animation for the view of [fragment] when
     *                    it's eventually popped from the stack.
     */
    fun add(fragment: Fragment,
            @AnimatorRes @AnimRes enterAnim: Int,
            @AnimatorRes @AnimRes exitAnim: Int,
            @AnimatorRes @AnimRes popEnterAnim: Int,
            @AnimatorRes @AnimRes popExitAnim: Int
    )

    /**
     * Replaces the top Fragment on the stack with [fragment].
     */
    fun replace(
            fragment: Fragment
    )

    /**
     * Replaces the top Fragment on the stack with [fragment], with
     * customized animations. All four animation parameters must be provided,
     * with a 0 value indicating if no animation should take place.
     *
     * @param fragment The new Fragment that will replace the _current_ one.
     * @param enterAnim The enter animation used for the view of the [fragment]
     *                  that's being added.
     * @param exitAnim The exit animation used for the view of the _current_
     *                 Fragment that's being removed.
     * @param popEnterAnim The enter animation for the view of the Fragment
     *                     that is revealed when [fragment] is popped.
     * @param popExitAnim The exit animation for the view of [fragment] when
     *                    it's eventually popped from the stack.
     */
    fun replace(
            fragment: Fragment,
            @AnimatorRes @AnimRes enterAnim: Int,
            @AnimatorRes @AnimRes exitAnim: Int,
            @AnimatorRes @AnimRes popEnterAnim: Int,
            @AnimatorRes @AnimRes popExitAnim: Int
    )

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
                ?: throw IllegalStateException("Fragment is not in an Activity that extends NavActivity")
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
