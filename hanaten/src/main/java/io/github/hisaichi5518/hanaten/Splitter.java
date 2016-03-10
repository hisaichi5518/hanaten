package io.github.hisaichi5518.hanaten;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Splitter<T> {
    private static final String SHARED_PREFERENCE_NAME = "hanaten";

    private String testName;
    private List<Variant> variants = new ArrayList<>();
    private float weights;
    private SharedPreferences sharedPreferences;

    public Splitter(@NonNull Context context, @NonNull String testName) {
        this.testName = testName;
        this.sharedPreferences = context.getApplicationContext().getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public Splitter<T> add(float weight, T value) {
        weights += weight;
        variants.add(new Variant<>(weight, value));

        return this;
    }

    public T split() {
        int index = sharedPreferences.getInt(testName, -1);
        if (index == -1 || !hasVariant(index)) {
            index = selectVariantIndex();
            saveVariantIndex(index);
        }

        return (T) variants.get(index).value;
    }

    boolean hasVariant(int index) {
        return variants.size() - 1 >= index;
    }

    int selectVariantIndex() {
        double index = Math.random() * weights;
        double cursor = 0;
        for (int i = 0; i < variants.size(); i++) {
            Variant variant = variants.get(i);
            cursor += variant.weight;
            if (index <= cursor) {
                return i;
            }
        }

        return -1;
    }

    private void saveVariantIndex(int index) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(testName, index);
        editor.apply();
    }
}

