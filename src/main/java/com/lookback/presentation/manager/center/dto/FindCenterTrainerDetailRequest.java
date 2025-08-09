package com.lookback.presentation.manager.center.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCenterTrainerDetailRequest {

    private Long centerId;
    private Long centerTrainerId;

}
