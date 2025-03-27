package com.lookback.presentation.exercise.controller;

import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.exercise.service.ExerciseService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.exercise.dto.ExerciseDto;
import com.lookback.presentation.exercise.dto.ExerciseTypeEnumDto;
import com.lookback.presentation.record.dto.FindRecordRequest;
import com.lookback.presentation.record.dto.FindRecordResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping("/exercises")
    public ResponseEntity<ApiResponse<T>> findExercises(
            HttpServletRequest request, HttpServletResponse response) {

        List<ExerciseTypeEnumDto> list = Arrays.stream(ExerciseTypeEnum.values()).map(
                (exerciseTypeEnum) -> ExerciseTypeEnumDto.of(exerciseTypeEnum.name(), exerciseTypeEnum.getMessage())
        ).toList();

        exerciseService.findExercises();

        return new ResponseEntity(ApiResponse.success(list, response), HttpStatus.OK);
    }

}
