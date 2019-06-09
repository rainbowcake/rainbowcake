package co.zsmb.rainbowcake.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * A [ViewModelProvider.Factory] implementation that provides the ViewModels bound by their class
 * keys in the ViewModelModule to the [RainbowCakeFragment] and [RainbowCakeActivity] classes.
 */
@Singleton
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value
        ?: throw IllegalArgumentException("Can't find ViewModel to inject ($modelClass) - perhaps it's not added to ViewModelModule?")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: ClassCastException) {
            throw IllegalStateException("Something went terribly wrong while instantiating a ViewModel", e)
        }
    }

}
