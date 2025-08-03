package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CenterProductOptionType {

    WORKOUT_CLOTHES("운동복"),
    SHOWER_ROOM("샤워실"),
    TOWEL("수건"),
    PRIVATE_LOCKER("개인 락커"),
    FREE_PARKING("무료주차");

    private final String message;

    CenterProductOptionType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

}
