package hu.autsoft.rainbowcake.extensions;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import hu.autsoft.rainbowcake.internal.QueuedSingleShotLiveData;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.arch.lifecycle.Lifecycle.Event.ON_START;
import static android.arch.lifecycle.Lifecycle.Event.ON_STOP;
import static org.junit.Assert.assertEquals;

public class QueuedSingleShotLiveDataTest implements LifecycleOwner {

    @Rule
    public TestRule instantExecutorRule = new InstantTaskExecutorRule();

    private LifecycleRegistry lifecycle = new LifecycleRegistry(this);

    private QueuedSingleShotLiveData<String> ble = new QueuedSingleShotLiveData<>();

    private static class MockObserver<T> implements Observer<T> {
        public List<T> observed = new ArrayList<>();

        @Override
        public void onChanged(@Nullable T t) {
            observed.add(t);
        }
    }

    private MockObserver<String> observer = new MockObserver<>();

    @Before
    public void setUp() throws Exception {
        ble.observe(this, observer);
        assertObserved();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    @Test
    public void startStopStart() {
        ble.setValue("a");
        assertObserved();
        lifecycle.handleLifecycleEvent(ON_START);
        assertObserved("a");
        ble.setValue("b");
        assertObserved("b");
        ble.setValue("c");
        lifecycle.handleLifecycleEvent(ON_STOP);
        ble.setValue("d");
        ble.setValue("e");
        assertObserved("c");
        lifecycle.handleLifecycleEvent(ON_START);
        assertObserved("d", "e");
    }

    @Test
    public void nullValue() {
        lifecycle.handleLifecycleEvent(ON_START);
        ble.setValue("a");
        ble.setValue(null);
        ble.setValue("b");
        assertObserved("a", null, "b");
    }

    @Test(expected = IllegalStateException.class)
    public void observeMultiple() {
        ble.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
    }

    @Test
    public void observeRemoveObserve() {
        lifecycle.handleLifecycleEvent(ON_START);
        ble.setValue("a");
        assertObserved("a");
        ble.removeObserver(observer);
        ble.setValue("c");
        ble.setValue("d");
        assertObserved();
        ble.observe(this, observer);
        assertObserved("c", "d");
    }

    public void assertObserved(String... expected) {
        assertEquals(Arrays.asList(expected), observer.observed);
        observer.observed.clear();
    }

}
