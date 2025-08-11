package com.lookback.domain.user.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.entity.CenterProductOption;
import com.lookback.domain.manager.center.service.CenterProductServiceValidate;
import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.TrainerInfo;
import com.lookback.domain.user.repository.TrainerRepository;
import com.lookback.presentation.manager.trainer.dto.FindTrainerBasicInfoResponse;
import com.lookback.presentation.manager.trainer.dto.FindTrainerDetailResponse;
import com.lookback.presentation.manager.trainer.dto.SaveTrainerProfileRequest;
import com.lookback.presentation.users.dto.TrainerInfoDto;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public FindTrainerBasicInfoResponse findTrainerBasicInfo(HttpServletRequest request) {
        UsersDto user = UserContext.getUser(request);

        Trainer findTrainer = trainerRepository.findByUsersId(user.getUserId());

        return FindTrainerBasicInfoResponse.fromEntity(findTrainer);
    }

    public FindTrainerDetailResponse findTrainerDetail(HttpServletRequest request) {
        UsersDto user = UserContext.getUser(request);

        Trainer findTrainer = trainerRepository.findByUsersId(user.getUserId());


        return FindTrainerDetailResponse.fromEntity(findTrainer);
    }

    @Transactional
    public void saveTrainerProfile(HttpServletRequest request, SaveTrainerProfileRequest saveTrainerProfileRequest) {

        TrainerServiceVaildator.saveTrainerProfile(saveTrainerProfileRequest);
        UsersDto user = UserContext.getUser(request);
        Long userId = user.getUserId();

        Trainer findTrainer = trainerRepository.findByUsersId(userId);

        boolean isCreate = findTrainer == null;
        boolean isUpdate = !isCreate;
        if (isCreate) {
            Trainer trainer = saveTrainerProfileRequest.toEntity();
            List<TrainerInfo> trainerInfos = saveTrainerProfileRequest.getTrainerInfos().stream().map(info -> info.toEntity(trainer)).toList();
            trainer.setTrainerInfos(trainerInfos);

            Trainer saveTrainer = trainerRepository.save(trainer);
            saveTrainer.getUser().setPhone(saveTrainerProfileRequest.getPhone());
        }

        if (isUpdate) {
            findTrainer.update(findTrainer, saveTrainerProfileRequest);
            findTrainer.getUser().setPhone(saveTrainerProfileRequest.getPhone());
        }


    }
}
