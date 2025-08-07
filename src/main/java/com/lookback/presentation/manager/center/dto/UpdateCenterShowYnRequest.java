package com.lookback.presentation.manager.center.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCenterShowYnRequest {
    private Long centerId;
    private String centerShowYn;
}
