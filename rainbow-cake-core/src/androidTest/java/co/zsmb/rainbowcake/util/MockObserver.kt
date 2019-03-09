package co.zsmb.rainbowcake.util

import android.arch.lifecycle.Observer
import org.junit.Assert
import java.util.ArrayList
import java.util.Arrays

class MockObserver<T> : Observer<T> {
    var observed: MutableList<T?> = ArrayList()

    override fun onChanged(t: T?) {
        observed.add(t)
    }

    fun assertObserved(vararg expected: T?) {
        Assert.assertEquals(Arrays.asList(*expected), observed)
        observed.clear()
    }
}