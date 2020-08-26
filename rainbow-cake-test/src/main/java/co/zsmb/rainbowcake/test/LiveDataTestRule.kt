package co.zsmb.rainbowcake.test

import android.annotation.SuppressLint
import co.zsmb.rainbowcake.internal.livedata.MainThreadWrapper
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor

public class LiveDataTestRule : TestRule {

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @SuppressLint("VisibleForTests")
            override fun evaluate() {
                // setup
                MainThreadWrapper.executor = Executor { it.run() }

                // run test
                base.evaluate()

                // teardown
                MainThreadWrapper.resetExecutor()
            }
        }
    }

}
