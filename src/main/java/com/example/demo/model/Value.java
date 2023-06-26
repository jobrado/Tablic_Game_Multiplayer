package com.example.demo.model;

public enum Value {

    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ACE(11),
    JACK(12),
    QUEEN(13),
    KING(14);
    private final int value;
    Value(int value) {
        this.value = value;
    }

    public int GetValue(){
        return value;
    }

    public static Value from(int value) {
        for (Value v : values()) {
            if (v.value == value) {
                return v;
            }
        }
        throw new RuntimeException("No such value");
    }

}
