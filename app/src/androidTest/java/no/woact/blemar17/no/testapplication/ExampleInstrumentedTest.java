package no.woact.blemar17.no.testapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    void useAppContext() {
        // Context of the app under test.
        final Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("app.test.kristiania.no.testapplication", appContext.getPackageName());
    }
}
