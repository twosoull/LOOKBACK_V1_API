package com.lookback.presentation.users.dto;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTrainingUsersRequest {

    private Long trainingId;
    private Long trainerId;
    private Long studentId;
    private TrainingStatus TrainingStatus;
}
