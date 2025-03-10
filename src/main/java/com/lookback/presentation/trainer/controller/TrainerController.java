package com.lookback.presentation.trainer.controller;

import com.lookback.domain.user.service.TrainingService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.trainer.dto.UserTrainingDto;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.FindTrainingUsersResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainerController {

    private final TrainingService trainingService;

    @GetMapping("/trainer/member")
    public ResponseEntity<ApiResponse<T>> trainerMember(
            HttpServletRequest request, HttpServletResponse response,
            FindTrainingUsersRequest findTrainingUsersRequest) {
        List<UserTrainingDto> findTrainingsForTrainer = trainingService.findTrainingsForTrainer(request ,findTrainingUsersRequest);
        return new ResponseEntity(ApiResponse.success(findTrainingsForTrainer, response), HttpStatus.OK);
    }
}
