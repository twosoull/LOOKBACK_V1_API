package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TrainingStatus {
    IN_PROGRESS("수업 중"),
    COMPLETED("수업 종료"),
    CANCELED("수업 해제");

    private final String message;

    TrainingStatus(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
