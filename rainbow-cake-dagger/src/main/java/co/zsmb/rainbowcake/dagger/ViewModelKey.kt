package co.zsmb.rainbowcake.dagger

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * The Dagger multibinding key to use for binding ViewModels to
 * identify each bound ViewModel by their class later.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
public annotation class ViewModelKey(val value: KClass<out ViewModel>)
