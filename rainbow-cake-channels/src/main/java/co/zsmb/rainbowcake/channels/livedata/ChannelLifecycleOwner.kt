package co.zsmb.rainbowcake.channels.livedata

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel


@UseExperimental(ExperimentalCoroutinesApi::class)
internal class ChannelLifecycleOwner(
        channel: Channel<*>
) : LifecycleOwner {

    private val lifecycle = LifecycleRegistry(this)

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        channel.invokeOnClose {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }
}
