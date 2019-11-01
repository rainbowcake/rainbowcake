package co.zsmb.rainbowcake.util

import android.arch.lifecycle.Observer
import org.junit.Assert
import java.util.ArrayList

class MockObserver<T> : Observer<T> {
    private val observed: MutableList<T?> = ArrayList()

    override fun onChanged(t: T?) {
        observed.add(t)
    }

    fun assertObserved(vararg expected: T?) {
        Assert.assertEquals(listOf(*expected), observed)
        observed.clear()
    }
}