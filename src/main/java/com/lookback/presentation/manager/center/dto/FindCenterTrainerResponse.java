package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterTrainer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCenterTrainerResponse {

    private Long centerId;
    private List<CenterTrainerDto> centerTrainers;
    private int page;
    private int totalCount;

    public static FindCenterTrainerResponse fromEntity(Long centerId, List<CenterTrainer> centerTrainers, int page, int totalCount) {
        return FindCenterTrainerResponse.builder()
                .centerId(centerId)
                .centerTrainers(centerTrainers.stream().map(
                        centerTrainer -> CenterTrainerDto.fromEntity(centerTrainer)
                ).toList())
                .page(page)
                .totalCount(totalCount)
                .build();
    }

}
