package com.lookback.presentation.manager.center.dto;

import com.lookback.common.converter.CommonConverter;
import com.lookback.domain.common.constant.enums.CenterTrainerRole;
import com.lookback.domain.common.constant.enums.CenterTrainerStatus;
import com.lookback.domain.manager.center.entity.CenterTrainer;
import com.lookback.presentation.users.dto.TrainerInfoDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCenterTrainerDetailResponse {

    private Long centerId;
    private Long centerTrainerId;
    private CenterTrainerRole centerTrainerRole;
    private TrainerDto trainer;
    private String phone;
    private String userName;
    private String nickName;
    private Long age;
    private String profileImageUrl;
    private String gender;
    private CenterTrainerStatus centerTrainerStatus;
    private List<TrainerInfoDto> trainerInfos;

    public static FindCenterTrainerDetailResponse fromEntity(Long centerId, CenterTrainer centerTrainer) {
        return FindCenterTrainerDetailResponse.builder()
                .centerTrainerRole(centerTrainer.getCenterTrainerRole())
                .trainer(TrainerDto.fromEntity(centerTrainer.getTrainer()))
                .phone(centerTrainer.getTrainer().getUser().getPhone())
                .userName(centerTrainer.getTrainer().getUser().getUserName())
                .nickName(centerTrainer.getTrainer().getUser().getNickName())
                .age(CommonConverter.ageConverter(centerTrainer.getTrainer().getUser().getBirthDt()))
                .profileImageUrl(centerTrainer.getTrainer().getUser().getProfileImageUrl())
                .gender(centerTrainer.getTrainer().getUser().getGender())
                .centerTrainerStatus(centerTrainer.getCenterTrainerStatus())
                .trainerInfos(centerTrainer.getTrainer().getTrainerInfos().stream().map(
                        info -> TrainerInfoDto.fromEntity(info)
                ).toList()).build();
    }

}
