package com.lookback.presentation.exercise.controller;

import com.lookback.presentation.exercise.dto.ExerciseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

    public ResponseEntity<ExerciseDto.Response> retrieveExercises(@RequestBody ExerciseDto.Request exerciseDtoRequest) {

        return null;
    }
}
