package com.lookback.domain.muscle.command;

import com.lookback.domain.muscle.entity.Muscle;
import com.lookback.domain.muscle.entity.MuscleCategory;

import java.time.LocalDateTime;

public class MuscleCommand {

    public record Save (Long muscleGroupId,
                        String muscleName,
                        String origin, //근육의 기시부(근육이 시작되는 부위)
                        String insertion, //근육의 정지부(근육이 끝나는 부위)
                        String role, //역할
                        String description,
                        Long activationLevel
                        ) {}

    public record Saved(Long muscleId,
                        Long muscleGroupId,
                        String muscleName,
                        String origin, //근육의 기시부(근육이 시작되는 부위)
                        String insertion, //근육의 정지부(근육이 끝나는 부위)
                        String role, //역할
                        String description,
                        Long activationLevel) {}

    public static MuscleCommand.Saved of(Muscle m) {
        return new MuscleCommand.Saved(m.getId(),
                                       m.getMuscleGroup().getId(),
                                       m.getMuscleName(),
                                       m.getOrigin(),
                                       m.getInsertion(),
                                       m.getRole(),
                                       m.getDescription(),
                                       m.getActivationLevel());
    }

}
