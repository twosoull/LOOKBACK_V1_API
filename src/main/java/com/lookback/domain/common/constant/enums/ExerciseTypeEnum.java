package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ExerciseTypeEnum {
    STRENGTH("근력"),
    STRETCHING("스트레칭"),
    CARDIO("유산소");

    private final String message;

    ExerciseTypeEnum(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

    public static String convertMessage(String exerciseType){
        return Arrays.stream(ExerciseTypeEnum.values())
                .filter(e -> e.name().equalsIgnoreCase(exerciseType))
                .map(e -> e.getMessage())
                .findFirst()
                .orElse(null);
    }
}
