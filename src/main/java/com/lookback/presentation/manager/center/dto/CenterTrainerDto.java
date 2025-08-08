package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.common.converter.CommonConverter;
import com.lookback.domain.common.constant.enums.CenterTrainerRole;
import com.lookback.domain.manager.center.entity.CenterTrainer;
import com.lookback.domain.user.entity.Trainer;
import com.lookback.presentation.users.dto.TrainerInfoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterTrainerDto {

    private Long centerTrainerId;
    private CenterTrainerRole centerTrainerRole;
    private TrainerDto trainer;
    private String userName;
    private String nickName;
    private Long age;
    private String profileImageUrl;
    private String gender;
    private List<TrainerInfoDto> trainerInfos;

    public static CenterTrainerDto fromEntity(CenterTrainer centerTrainer) {
        return CenterTrainerDto.builder()
                .centerTrainerRole(centerTrainer.getCenterTrainerRole())
                .trainer(TrainerDto.fromEntity(centerTrainer.getTrainer()))
                .userName(centerTrainer.getTrainer().getUser().getUserName())
                .nickName(centerTrainer.getTrainer().getUser().getNickName())
                .age(CommonConverter.ageConverter(centerTrainer.getTrainer().getUser().getBirthDt()))
                .profileImageUrl(centerTrainer.getTrainer().getUser().getProfileImageUrl())
                .gender(centerTrainer.getTrainer().getUser().getGender())
                .trainerInfos(centerTrainer.getTrainer().getTrainerInfos().stream().map(
                        info -> TrainerInfoDto.fromEntity(info)
                ).toList()).build();
    }
}
