package com.lookback.presentation.trainer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberRequest {

    private Long trainerId;
    private Long memberId;

}
