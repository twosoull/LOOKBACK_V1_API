package com.lookback.domain.record.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.presentation.record.dto.RemoveRecordRequest;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.SaveTrainingUserRequest;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

public class RecordServiceValidator {

    public static void removeRecordRequestValid(RemoveRecordRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("request is null");
        }

        if(request.getRecordId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }

}
