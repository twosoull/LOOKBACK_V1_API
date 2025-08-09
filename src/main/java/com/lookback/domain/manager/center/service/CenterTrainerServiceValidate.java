package com.lookback.domain.manager.center.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.manager.center.repository.CenterTrainerRepository;
import com.lookback.domain.user.repository.TrainerRepository;
import com.lookback.presentation.manager.center.dto.*;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.*;

@RequiredArgsConstructor
public class CenterTrainerServiceValidate {

    public static void FindCenterTrainers(FindCenterTrainerRequest findCenterTrainerRequest) {
        if (findCenterTrainerRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
    }

    public static void FindCenterTrainerDetail(FindCenterTrainerDetailRequest findCenterTrainerDetailRequest) {
        if (findCenterTrainerDetailRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        if (findCenterTrainerDetailRequest.getCenterTrainerId() == null) {
            throw new RestApiException(NOT_FOUND_TRAINER);
        }
    }

    public static void updateCenterTrainerStatus(UpdateCenterTrainerStatusRequest updateCenterTrainerStatusRequest) {
        if (updateCenterTrainerStatusRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        if (updateCenterTrainerStatusRequest.getCenterTrainerId() == null) {
            throw new RestApiException(NOT_FOUND_TRAINER);
        }

        if (updateCenterTrainerStatusRequest.getCenterTrainerStatus() == null){
            throw new RestApiException(NOT_FOUND_TRAINER_STATUS);
        }
    }

    public static void updateCenterTrainerStatusExclusion(UpdateCenterTrainerStatusExclusionRequest updateCenterTrainerStatusExclusionRequest) {
        if (updateCenterTrainerStatusExclusionRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        if (updateCenterTrainerStatusExclusionRequest.getCenterTrainerId() == null) {
            throw new RestApiException(NOT_FOUND_TRAINER);
        }
    }

    public static void updateCenterTrainerRoleSuper( UpdateCenterTrainerRoleSuperRequest updateCenterTrainerRoleSuperRequest) {

        if (updateCenterTrainerRoleSuperRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        if (updateCenterTrainerRoleSuperRequest.getCenterTrainerId() == null) {
            throw new RestApiException(NOT_FOUND_TRAINER);
        }
    }

    public static void updateCenterTrainerRoleGeneral(UpdateCenterTrainerRoleGeneralRequest updateCenterTrainerRoleGeneralRequest) {
        if (updateCenterTrainerRoleGeneralRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        if (updateCenterTrainerRoleGeneralRequest.getCenterTrainerId() == null) {
            throw new RestApiException(NOT_FOUND_TRAINER);
        }
    }

    public static void findCenterTrainerProducts(FindCenterTrainerProductRequest findCenterTrainerProductRequest) {
        if (findCenterTrainerProductRequest.getCenterId() == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        if (findCenterTrainerProductRequest.getCenterTrainerId() == null) {
            throw new RestApiException(NOT_FOUND_TRAINER);
        }

    }
}