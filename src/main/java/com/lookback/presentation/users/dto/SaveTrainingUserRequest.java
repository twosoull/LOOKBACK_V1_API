package com.lookback.presentation.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTrainingUserRequest {

    private Long trainerId;
    private Long studentId;
    private String trainingStatus;



}
