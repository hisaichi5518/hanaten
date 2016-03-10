package io.github.hisaichi5518.hanaten;

class Variant<T> {
    float weight;
    T value;

    public Variant(float weight, T value) {
        this.weight = weight;
        this.value = value;
    }
}
