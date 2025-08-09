package com.lookback.domain.common.constant.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum CenterTrainerStatus {

    REQUESTED("REQUESTED"), //"승인요청"
    APPROVED("APPROVED"), //"승인"
    EXCLUSION("EXCLUSION"), // 제외
    REFUSAL("REFUSAL"), //"거절"
    LEFT("LEFT"); // 나감

    @Getter
    private final String code;

    CenterTrainerStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
