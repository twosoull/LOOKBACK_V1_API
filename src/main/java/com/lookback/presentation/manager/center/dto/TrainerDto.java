package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.trainer.dto.UserTrainingDto;
import com.lookback.presentation.users.dto.UsersDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerDto {

    private Long trainerId;

    private UsersDto user;

    private List<UserTrainingDto> userTrainings;

    private String selfIntroduction;

    public static TrainerDto fromEntity(Trainer trainer) {
        return TrainerDto.builder()
                .trainerId(trainer.getId())
                .selfIntroduction(trainer.getSelfIntroduction())
                .build();
    }

}
