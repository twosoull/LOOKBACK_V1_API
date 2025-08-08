package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TrainerInfoType {

    EDUCATION("EDUCATION"),             // 학력
    CAREER("CAREER"),                // 경력
    CERTIFICATE("CERTIFICATE"),         // 자격증
    TRAINING("TRAINING"),         // 교육 & 수료
    AWARD("AWARD"),                 // 수상
    TAG("TAG");                   // 태그

    private final String message;

    TrainerInfoType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }


}
