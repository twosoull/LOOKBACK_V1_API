package com.lookback.presentation.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindTrainingUsersRequest {
    private Long trainingId;
    private String trainingStatus;
}
