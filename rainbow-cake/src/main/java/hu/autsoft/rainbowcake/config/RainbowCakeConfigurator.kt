package hu.autsoft.rainbowcake.config

interface RainbowCakeConfigurator {

    var isDebug: Boolean

    var logger: LoggingOption

    var consumeExecuteExceptions: Boolean

}
