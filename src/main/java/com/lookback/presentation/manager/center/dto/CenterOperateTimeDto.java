package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.CenterFacility;
import com.lookback.domain.manager.center.entity.CenterOperateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class CenterOperateTimeDto {
    private Long centerOperateTimeId;
    private String centerOperateDay;
    private LocalTime centerOperateStartTime;
    private LocalTime centerOperateEndTime;
    private String centerOperateUseYn;

    public static CenterOperateTimeDto fromEntity(CenterOperateTime entity) {
        return CenterOperateTimeDto.builder()
                .centerOperateTimeId(entity.getId())
                .centerOperateDay(entity.getCenterOperateDay())
                .centerOperateStartTime(entity.getCenterOperateStartTime())
                .centerOperateEndTime(entity.getCenterOperateEndTime())
                .centerOperateUseYn(entity.getCenterOperateUseYn())
                .build();
    }

}
