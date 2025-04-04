package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExerciseDetailTypeEnum {
    STRENGTH("근력"),
    STRETCHING("스트레칭"),
    TIME("시간"),
    DISTANCE("거리"),
    SPEED("속도"),
    INCLINE("경사"),
    HEART_RATE("심박수"),
    CALORIES("칼로리");

    private final String message;

    ExerciseDetailTypeEnum(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }


}
