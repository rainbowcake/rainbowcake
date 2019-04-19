@file:Suppress("NOTHING_TO_INLINE")

package co.zsmb.rainbowcake.extensions

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Adds an arguments Bundle to the receiver Fragment, performing [argSetup] on the Bundle.
 *
 * @return the Fragment instance
 */
@Deprecated(
        message = "Use applyArgs instead.",
        replaceWith = ReplaceWith("applyArgs(argSetup)"),
        level = DeprecationLevel.ERROR
)
inline fun <T : Fragment> T.withArgs(argSetup: Bundle.() -> Unit): T = apply {
    val bundle = Bundle()
    bundle.argSetup()
    arguments = bundle
}

/**
 * Adds an arguments Bundle to the receiver Fragment, performing [argSetup] on the Bundle.
 *
 * @return the Fragment instance
 */
inline fun <T : Fragment> T.applyArgs(argSetup: Bundle.() -> Unit): T = apply {
    val bundle = Bundle()
    bundle.argSetup()
    arguments = bundle
}

/**
 * Return the arguments supplied when the Fragment was instantiated.
 *
 * @throws IllegalStateException if the Fragment doesn't have an associated arguments Bundle.
 */
inline fun Fragment.requireArguments(): Bundle {
    return arguments ?: throw IllegalStateException("Fragment has no arguments Bundle.")
}
