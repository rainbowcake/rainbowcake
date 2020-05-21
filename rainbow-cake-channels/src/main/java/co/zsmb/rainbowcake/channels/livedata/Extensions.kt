package co.zsmb.rainbowcake.channels.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

@Deprecated(
        "Channel support is being removed",
        level = DeprecationLevel.WARNING
)
fun <T> LiveData<T>.toChannel(): ReceiveChannel<T> {
    val channel = Channel<T>(capacity = Channel.CONFLATED)
    val lifecycleOwner = ChannelLifecycleOwner(channel)
    observe(lifecycleOwner, Observer {
        if (it != null) {
            channel.offer(it)
        }
    })
    return channel
}
