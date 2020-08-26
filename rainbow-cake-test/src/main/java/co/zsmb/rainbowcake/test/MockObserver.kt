@file:Suppress("NOTHING_TO_INLINE")

package co.zsmb.rainbowcake.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import org.junit.Assert

/**
 * An observer that can be used to collect state updates or events
 * from a [RainbowCakeViewModel]. These values can be asserted on:
 * see the extensions for this type.
 */
public interface MockObserver<T : Any> {
    public val observed: MutableList<T?>
}

/**
 * A simple [LiveData] observer that collects all observed elements
 * into a [MutableList].
 */
internal class MockLiveDataObserver<T : Any> : Observer<T>, MockObserver<T> {
    override val observed: MutableList<T?> = mutableListOf()

    override fun onChanged(t: T?) {
        observed.add(t)
    }
}

/**
 * Asserts that the [expected] elements (and only those) have been
 * observed by the observer, in the correct order.
 */
public inline fun <T : Any> MockObserver<T>.assertObserved(vararg expected: T?) {
    Assert.assertEquals(listOf(*expected), observed)
}

/**
 * Asserts that at one point, the [expected] element has been observed
 * by the observer, at least once.
 */
public inline fun <T : Any> MockObserver<T>.assertDidObserve(expected: T?) {
    Assert.assertTrue(expected in observed)
}

/**
 * Asserts that [expected] is the element last seen by the observer.
 */
public inline fun <T : Any> MockObserver<T>.assertObservedLast(expected: T?) {
    Assert.assertEquals(expected, observed.last())
}

/**
 * Clears all previously observed elements from the observer.
 */
public inline fun <T : Any> MockObserver<T>.reset() {
    observed.clear()
}
