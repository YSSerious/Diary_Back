package com.example.diary.enums;

public enum FoodHealthy {
    GOOD(4),
    NORMAL(3),
    NOT_GOOD(2),
    BAD(1),
    DRINK(-1);

    private int value;

    FoodHealthy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
