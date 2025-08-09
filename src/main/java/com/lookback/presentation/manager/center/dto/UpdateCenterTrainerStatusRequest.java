package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.common.constant.enums.CenterTrainerStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCenterTrainerStatusRequest {

    private Long centerId;
    private Long centerTrainerId;
    private CenterTrainerStatus centerTrainerStatus;

}
