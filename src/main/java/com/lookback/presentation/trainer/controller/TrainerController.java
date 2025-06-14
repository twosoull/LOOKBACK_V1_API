package com.lookback.presentation.trainer.controller;

import com.lookback.domain.user.service.TrainingService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.trainer.dto.*;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrainerController {

    private final TrainingService trainingService;

    @GetMapping("/trainer/member")
    public ResponseEntity<ApiResponse<T>> trainerMember(
            HttpServletRequest request, HttpServletResponse response,
            FindTrainingUsersRequest findTrainingUsersRequest) {
        UserTrainingDto findTrainingsForTrainer = trainingService.findTrainingsForTrainer(request ,findTrainingUsersRequest);
        return new ResponseEntity(ApiResponse.success(findTrainingsForTrainer, response), HttpStatus.OK);
    }

    @PostMapping("/trainer/cancel")
    public ResponseEntity<ApiResponse<T>> cancelTraining(
            HttpServletResponse response,
            @RequestBody UpdateTrainingUsersRequest updateTrainingUsersRequest) {

        CancelTrainingDto cancelTrainingDto = trainingService.cancelTraining(updateTrainingUsersRequest);
        return new ResponseEntity(ApiResponse.success(cancelTrainingDto, response), HttpStatus.OK);
    }

    @GetMapping("/trainer/addMember/info")
    public ResponseEntity<ApiResponse<T>> addMemberInfo(
            HttpServletRequest request,
            HttpServletResponse response,
            AddMemberRequest addMemberRequest) {
        AddMemberDto addMemberDto = trainingService.addMemberInfo(request, addMemberRequest);
        return new ResponseEntity(ApiResponse.success(addMemberDto, response), HttpStatus.OK);
    }

    @PostMapping("/trainer/addMember")
    public ResponseEntity<ApiResponse<T>> addMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AddMemberRequest addMemberRequest) {
        AddMemberDto addMemberDto = trainingService.addMember(request, addMemberRequest);
        return new ResponseEntity(ApiResponse.success(addMemberDto, response), HttpStatus.OK);
    }
}
