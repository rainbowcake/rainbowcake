package co.zsmb.rainbowcake.test.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import co.zsmb.rainbowcake.test.CoroutineIODispatcherTestRule
import co.zsmb.rainbowcake.test.CoroutineMainDispatcherTestRule
import co.zsmb.rainbowcake.test.LiveDataTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

/**
 * A base class for ViewModel tests, where [CoroutineMainDispatcherTestRule]
 * and [CoroutineIODispatcherTestRule] has to be enabled to mock the Main and
 * IO dispatchers, while [InstantTaskExecutorRule] and [LiveDataTestRule] are
 * required to mock the main thread used by regular and custom [LiveData]
 * instances, respectively.
 */
@ExperimentalCoroutinesApi
abstract class ViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val ioDispatcherTestRule = CoroutineIODispatcherTestRule()

    @get:Rule
    val mainDispatcherTestRule = CoroutineMainDispatcherTestRule()

    @get:Rule
    val liveDataTestRule = LiveDataTestRule()

}
