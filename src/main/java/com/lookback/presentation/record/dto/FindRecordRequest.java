package com.lookback.presentation.record.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindRecordRequest {

    private Long userId;
    private Long recordId;
    private String userType; //TRAINER, MEMBER

    private String type;  //all, pt, personal
}
