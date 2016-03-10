package io.github.hisaichi5518.hanaten;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SplitterTest {

    @Test
    public void testSplit() throws Exception {
        Splitter<Integer> splitter = new Splitter<>(RuntimeEnvironment.application, "testSplit");
        splitter.add(0.5F, 1);
        splitter.add(0.5F, 2);

        int beforeResult = 0;
        for (int i = 0; i < 1_000_000; i++) {
            int result = splitter.split();
            if (beforeResult > 0) {
                assertThat(result).isEqualTo(beforeResult);
            } else {
                System.out.print("First result is " + String.valueOf(result));
            }
            beforeResult = result;
        }
    }

    @Test
    public void testHasVariant() throws Exception {
        Splitter<Integer> splitter = new Splitter<>(RuntimeEnvironment.application, "testHasVariant");
        splitter.add(0.5F, 1);
        splitter.add(0.5F, 2);

        assertThat(splitter.hasVariant(0)).isTrue();
        assertThat(splitter.hasVariant(1)).isTrue();
        assertThat(splitter.hasVariant(2)).isFalse();
    }

    @Test
    public void testSelectVariantIndex() throws Exception {
        Splitter<Integer> splitter = new Splitter<>(RuntimeEnvironment.application, "testSplit");
        splitter.add(0.5F, 1);
        splitter.add(0.5F, 2);

        int result1 = 0;
        int result2 = 0;
        int loopCount = 0;
        for (int i = 0; i < 1_000_000; i++) {
            loopCount++;
            int result = splitter.selectVariantIndex();
            assertThat(result).isNotEqualTo(-1);

            if (result == 0) {
                result1++;
            } else if (result == 1) {
                result2++;
            } else {
                fail("not support index: " + result);
            }
        }

        assertThat(result1 + result2).isEqualTo(loopCount);
        assertThat(2.0*0.95 <= result2 / result1 && result2 / result1 <= 2.0*1.05);
    }
}