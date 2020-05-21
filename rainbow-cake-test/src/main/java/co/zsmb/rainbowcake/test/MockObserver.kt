@file:Suppress("NOTHING_TO_INLINE")

package co.zsmb.rainbowcake.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Assert

/**
 * A simple [LiveData] observer that collects all observed elements
 * into a [MutableList].
 */
class MockObserver<T> : Observer<T> {
    val observed: MutableList<T?> = mutableListOf()

    override fun onChanged(t: T?) {
        observed.add(t)
    }
}

/**
 * Asserts that the [expected] elements (and only those) have been
 * observed by the observer, in the correct order.
 */
inline fun <T> MockObserver<T>.assertObserved(vararg expected: T?) {
    Assert.assertEquals(listOf(*expected), observed)
}

/**
 * Asserts that at one point, the [expected] element has been observed
 * by the observer, at least once.
 */
inline fun <T> MockObserver<T>.assertDidObserve(expected: T?) {
    Assert.assertTrue(expected in observed)
}

/**
 * Asserts that [expected] is the element last seen by the observer.
 */
inline fun <T> MockObserver<T>.assertObservedLast(expected: T?) {
    Assert.assertEquals(expected, observed.last())
}

/**
 * Clears all previously observed elements from the observer.
 */
inline fun <T> MockObserver<T>.reset() {
    observed.clear()
}
