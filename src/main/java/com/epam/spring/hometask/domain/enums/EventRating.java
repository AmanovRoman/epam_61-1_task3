package com.epam.spring.hometask.domain.enums;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {

    LOW (1),

    MID (2),

    HIGH (3);

    private int value;
    EventRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


