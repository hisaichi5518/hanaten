package io.github.hisaichi5518.hanaten;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HanatenTest {
    @Test
    public void testStart() {
        Hanaten hanaten = new Hanaten(RuntimeEnvironment.application, "testStart");

        final AtomicInteger result1 = new AtomicInteger();
        final AtomicInteger result2 = new AtomicInteger();
        hanaten.add(0.5F, new Runnable() {
            @Override
            public void run() {
                result1.getAndIncrement();
            }
        })
        .add(0.5F, new Runnable() {
            @Override
            public void run() {
                result2.getAndIncrement();
            }
        });

        int loopCount = 0;
        for (int i = 0; i < 1_000_000; i++) {
            loopCount++;
            hanaten.start();

            if (result1.get() > 0) {
                assertThat(result2.get()).isZero();
                assertThat(result1.get()).isEqualTo(loopCount);
            } else if (result2.get() > 0) {
                assertThat(result1.get()).isZero();
                assertThat(result2.get()).isEqualTo(loopCount);
            } else {
                fail("result1 and result2 is zero.");
            }
        }
    }
}