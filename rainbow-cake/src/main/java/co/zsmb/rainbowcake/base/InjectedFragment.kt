package co.zsmb.rainbowcake.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import co.zsmb.rainbowcake.BaseApplication
import javax.inject.Inject

/**
 * A non-generic base class for Dagger to inject with a [ViewModelProvider.Factory] instance.
 */
abstract class InjectedFragment : Fragment() {

    @PublishedApi
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as? BaseApplication)
                ?.injector
                ?.inject(this)
                ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from BaseApplication")
    }

}
