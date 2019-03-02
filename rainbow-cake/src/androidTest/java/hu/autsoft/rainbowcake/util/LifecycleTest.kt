package hu.autsoft.rainbowcake.util

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import org.junit.Rule
import org.junit.rules.TestRule

abstract class LifecycleTest : LifecycleOwner {

    @JvmField
    @Rule
    var instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Suppress("LeakingThis")
    protected val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

}
