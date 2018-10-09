package hu.autsoft.rainbowcake.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hu.autsoft.rainbowcake.BaseApplication
import javax.inject.Inject

/**
 * A non-generic base class for Dagger to inject with a [ViewModelProvider.Factory] instance.
 */
abstract class InjectedActivity : AppCompatActivity() {

    @PublishedApi
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as? BaseApplication)
                ?.injector
                ?.inject(this)
                ?: throw IllegalStateException("InjectedActivity should not be used without an Application that inherits from BaseApplication")
    }

}
