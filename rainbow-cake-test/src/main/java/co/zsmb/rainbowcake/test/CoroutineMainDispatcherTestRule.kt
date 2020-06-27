package co.zsmb.rainbowcake.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * A JUnit 4 test rule to be used in ViewModel tests.
 *
 * Forces coroutine code that otherwise uses the Main dispatcher to
 * run on the single test thread.
 */
@ExperimentalCoroutinesApi
class CoroutineMainDispatcherTestRule : TestRule {

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                // setup
                Dispatchers.setMain(testDispatcher)

                // run test
                base.evaluate()

                // teardown
                Dispatchers.resetMain()
                testDispatcher.cleanupTestCoroutines()
            }
        }
    }

}
