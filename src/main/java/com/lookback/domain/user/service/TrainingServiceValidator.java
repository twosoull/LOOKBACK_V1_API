package com.lookback.domain.user.service;

import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.SaveTrainingUserRequest;

public class TrainingServiceValidator {

    public static void findTrainingUsersRequestValid(FindTrainingUsersRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("request is null");
        }
    }

    public static void saveTrainingUserRequestValid(SaveTrainingUserRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("request is null");
        }
    }
}
