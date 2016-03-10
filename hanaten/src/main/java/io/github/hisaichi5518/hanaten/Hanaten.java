package io.github.hisaichi5518.hanaten;

import android.content.Context;
import android.support.annotation.NonNull;

public class Hanaten {
    private Splitter<Runnable> splitter;

    public Hanaten(@NonNull Context context, @NonNull String testName) {
        splitter = new Splitter<>(context, testName);
    }

    public Hanaten add(float weight, Runnable runnable) {
        splitter.add(weight, runnable);
        return this;
    }

    public void start() {
        Runnable runnable = splitter.split();
        runnable.run();
    }
}
