package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TrainerInfoType {

    EDUCATION("학력"),             // 학력
    CAREER("경력"),                // 경력
    CERTIFICATE("자격증"),         // 자격증
    TRAINING("교육&수료"),         // 교육 & 수료
    AWARD("수상"),                 // 수상
    TAG("태그");                   // 태그

    private final String message;

    TrainerInfoType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }


}
