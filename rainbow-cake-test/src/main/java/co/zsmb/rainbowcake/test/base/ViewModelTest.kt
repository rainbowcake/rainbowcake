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
public abstract class ViewModelTest {

    @get:Rule
    public val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    public val ioDispatcherTestRule: CoroutineIODispatcherTestRule = CoroutineIODispatcherTestRule()

    @get:Rule
    public val mainDispatcherTestRule: CoroutineMainDispatcherTestRule = CoroutineMainDispatcherTestRule()

    @get:Rule
    public val liveDataTestRule: LiveDataTestRule = LiveDataTestRule()

}
