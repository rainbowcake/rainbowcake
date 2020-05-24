package co.zsmb.rainbowcake.test.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import co.zsmb.rainbowcake.test.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

/**
 * A base class for ViewModel tests, where the [CoroutineTestRule]
 * has to be enabled to mock the Main and IO dispatchers, and the
 * [InstantTaskExecutorRule] is required to mock the main thread
 * used by [LiveData] instances.
 */
@ExperimentalCoroutinesApi
abstract class ViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

}
