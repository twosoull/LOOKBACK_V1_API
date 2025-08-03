package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CenterProductType {

    MEMBERSHIP("회원권"),             // 정기 회원권
    DAILY_PASS("일일권"),            // 일일 이용권
    PT_PASS("PT수강권"),             // PT 수강권
    ETC("기타");                     // 기타

    private final String message;

    CenterProductType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

}
