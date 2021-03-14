package com.example.diary.enums;

public enum BodyResponse {
    GOOD(4),
    NORMAL(3),
    NOT_GOOD(2),
    BAD(1);

    private int value;

    BodyResponse(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
