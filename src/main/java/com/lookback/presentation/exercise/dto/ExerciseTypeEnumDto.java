package com.lookback.presentation.exercise.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExerciseTypeEnumDto {

    private String key;
    private String value;

    public ExerciseTypeEnumDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static ExerciseTypeEnumDto of(String key, String value) {
        return new ExerciseTypeEnumDto(key, value);
    }
}
