package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.manager.center.entity.CenterTrainer;
import com.lookback.domain.manager.center.repository.CenterTrainerRepository;
import com.lookback.domain.user.repository.TrainerRepository;
import com.lookback.presentation.manager.center.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CenterTrainerService {

    private final CenterTrainerRepository centerTrainerRepository;
    private final TrainerRepository trainerRepository;

    public FindCenterTrainerResponse findCenterTrainers(FindCenterTrainerRequest findCenterTrainerRequest, Pageable pageable) {
        CenterTrainerServiceValidate.FindCenterTrainers(findCenterTrainerRequest);

        Page<CenterTrainer> findCenterTrainers = centerTrainerRepository.findByCenterId(findCenterTrainerRequest.getCenterId(), pageable);

        List<CenterTrainer> centerTrainers = findCenterTrainers.getContent();
        int totalCount = findCenterTrainers.getTotalPages();
        int page = findCenterTrainers.getNumber();

        return FindCenterTrainerResponse.fromEntity(findCenterTrainerRequest.getCenterId(), centerTrainers, page, totalCount);

    }

    public FindCenterTrainerDetailResponse findCenterTrainer(FindCenterTrainerDetailRequest findCenterTrainerDetailRequest) {
        CenterTrainerServiceValidate.FindCenterTrainerDetail(findCenterTrainerDetailRequest);

        CenterTrainer centerTrainer = centerTrainerRepository
                .findByIdAndCenterId(
                        findCenterTrainerDetailRequest.getCenterTrainerId(),
                        findCenterTrainerDetailRequest.getCenterId());


        return FindCenterTrainerDetailResponse.fromEntity(findCenterTrainerDetailRequest.getCenterId(),
                                                            centerTrainer);
    }


    @Transactional
    public void updateCenterTrainerStatus(UpdateCenterTrainerStatusRequest updateCenterTrainerStatusRequest) {
        CenterTrainerServiceValidate.updateCenterTrainerStatus(updateCenterTrainerStatusRequest);

        // TODO 승인요청 일시, 승인일시, 거절일시, 트레이너소속제외 일시는 DB에 저장
        CenterTrainer centerTrainer = centerTrainerRepository
                .findByIdAndCenterId(
                        updateCenterTrainerStatusRequest.getCenterTrainerId(),
                        updateCenterTrainerStatusRequest.getCenterId());

        if (centerTrainer == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        try {
            centerTrainer.updateStatus(updateCenterTrainerStatusRequest.getCenterTrainerStatus());
        } catch (Exception e) {
            throw new RestApiException(NOT_UPDATE_TRAINER_STATUS);
        }

    }

    @Transactional
    public void updateCenterTrainerStatusExclusion(UpdateCenterTrainerStatusExclusionRequest updateCenterTrainerStatusExclusionRequest) {
        CenterTrainerServiceValidate.updateCenterTrainerStatusExclusion(updateCenterTrainerStatusExclusionRequest);

        // TODO 승인요청 일시, 승인일시, 거절일시, 트레이너소속제외 일시는 DB에 저장
        CenterTrainer centerTrainer = centerTrainerRepository
                .findByIdAndCenterId(
                        updateCenterTrainerStatusExclusionRequest.getCenterTrainerId(),
                        updateCenterTrainerStatusExclusionRequest.getCenterId());

        if (centerTrainer == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        try {
            centerTrainer.updateStatusExclusion();
        } catch (Exception e) {
            throw new RestApiException(NOT_UPDATE_TRAINER_STATUS);
        }
    }

    @Transactional
    public void updateCenterTrainerRoleSuper(HttpServletRequest request, UpdateCenterTrainerRoleSuperRequest updateCenterTrainerRoleSuperRequest) {

        // TODO 사용자가 해당 센터의 소유자인지 valid TEST 필요.. 공통만들기 고민
        /*
        // UsersDto user = UserContext.getUser(request);
        Trainer findTrainer = trainerRepository.findByUsersId(user.getUserId());
        Long trainerId= findTrainer.getId();
        CenterTrainer findCenterTrainer = centerTrainerRepository.findByTrainerIdAndCenterId(trainerId, updateCenterTrainerRoleSuperRequest.getCenterId());
        if (!findCenterTrainer.getCenterTrainerRole().equals(CenterTrainerRole.OWNER)) {
            throw new RestApiException(NOT_HAS_ROLE);
        }
        */
        CenterTrainerServiceValidate.updateCenterTrainerRoleSuper(updateCenterTrainerRoleSuperRequest);


        // TODO 승인요청 일시, 승인일시, 거절일시, 트레이너소속제외 일시는 DB에 저장
        CenterTrainer centerTrainer = centerTrainerRepository
                .findByIdAndCenterId(
                        updateCenterTrainerRoleSuperRequest.getCenterTrainerId(),
                        updateCenterTrainerRoleSuperRequest.getCenterId());

        if (centerTrainer == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        try {
            centerTrainer.updateRoleSuperAdmin();
        } catch (Exception e) {
            throw new RestApiException(NOT_UPDATE_TRAINER_ROLE);
        }

    }

    @Transactional
    public void updateCenterTrainerRoleGeneral(HttpServletRequest request, UpdateCenterTrainerRoleGeneralRequest updateCenterTrainerRoleGeneralRequest) {
        // TODO 사용자가 해당 센터의 최고관리자, 소유자인지 valid TEST 필요.. 공통만들기 고민

        // UsersDto user = UserContext.getUser(request);
        /*
        Trainer findTrainer = trainerRepository.findByUsersId(user.getUserId());
        Long trainerId= findTrainer.getId();
        CenterTrainer findCenterTrainer = centerTrainerRepository.findByTrainerIdAndCenterId(trainerId, updateCenterTrainerRoleSuperRequest.getCenterId());
        if (!findCenterTrainer.getCenterTrainerRole().equals(CenterTrainerRole.GENERAL))) {
            throw new RestApiException(NOT_HAS_ROLE);
        }
        */
        CenterTrainerServiceValidate.updateCenterTrainerRoleGeneral(updateCenterTrainerRoleGeneralRequest);


        // TODO 승인요청 일시, 승인일시, 거절일시, 트레이너소속제외 일시는 DB에 저장
        CenterTrainer centerTrainer = centerTrainerRepository
                .findByIdAndCenterId(
                        updateCenterTrainerRoleGeneralRequest.getCenterTrainerId(),
                        updateCenterTrainerRoleGeneralRequest.getCenterId());

        if (centerTrainer == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        try {
            centerTrainer.updateRoleGeneral();
        } catch (Exception e) {
            throw new RestApiException(NOT_UPDATE_TRAINER_ROLE);
        }
    }

}
