package hu.autsoft.rainbowcake.internal.logging

import android.util.Log
import hu.autsoft.rainbowcake.config.Logger

internal object AndroidLogger : Logger {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }
}

internal object BlankLogger : Logger {
    override fun log(tag: String, message: String) {
        /* empty */
    }
}
