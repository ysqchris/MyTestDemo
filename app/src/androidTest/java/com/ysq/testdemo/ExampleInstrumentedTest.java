package com.ysq.testdemo;

import android.app.Instrumentation;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

// 添加构建  提交后构建
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test

        Instrumentation appContext = InstrumentationRegistry.getInstrumentation();

        //assertEquals("com.ysq.testdemo", appContext.getPackageName());
    }
}
