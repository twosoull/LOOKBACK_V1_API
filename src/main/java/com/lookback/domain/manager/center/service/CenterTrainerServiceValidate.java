package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.presentation.manager.center.dto.*;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

public class CenterTrainerServiceValidate {

    public static void FindCenterTrainer(FindCenterTrainerRequest findCenterTrainerRequest) {
        if (findCenterTrainerRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }
}
