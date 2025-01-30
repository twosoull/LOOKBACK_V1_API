package com.lookback.presentation.record.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindRecordRequest {

    private Long userId;
    private Long recordId;

    private String type;  //all, pt, personal
}
