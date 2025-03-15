package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExerciseTypeEnum {
    STRENGTH("근력"),
    CARDIO("유산소"),
    STRETCHING("스트레칭");

    private final String message;

    ExerciseTypeEnum(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
