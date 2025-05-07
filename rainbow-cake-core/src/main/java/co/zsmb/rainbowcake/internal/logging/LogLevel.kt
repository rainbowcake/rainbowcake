package co.zsmb.rainbowcake.internal.logging

import android.util.Log

public enum class LogLevel(public val priority: Int = Log.DEBUG) {
    VERBOSE(Log.VERBOSE),
    DEBUG(Log.DEBUG),
    INFO(Log.INFO),
    WARN(Log.WARN),
    ERROR(Log.ERROR),
    ASSERT(Log.ASSERT),
    OFF(Int.MAX_VALUE);
}
