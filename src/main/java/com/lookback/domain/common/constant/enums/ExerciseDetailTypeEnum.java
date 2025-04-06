package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    //front에서 데이터가 key값으로 넘어올 경우 사용
    @JsonCreator
    public static ExerciseDetailTypeEnum from(String value) {
        // 영문 KEY로 파싱 (프론트에서 오는 값 기준)
        for (ExerciseDetailTypeEnum type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

}
