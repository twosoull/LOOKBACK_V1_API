package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CenterProductType {

    MEMBERSHIP("MEMBERSHIP"),             // 정기 회원권
    DAILY_PASS("DAILYPASS"),            // 일일 이용권
    PT_PASS("PTPASS"),             // PT 수강권
    ETC("ETC");                     // 기타

    private final String message;

    CenterProductType(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

}
