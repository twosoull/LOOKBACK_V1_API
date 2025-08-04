package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterFacility;
import com.lookback.domain.manager.center.entity.CenterOperateTime;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenterOperateTimeDto {
    private Long centerOperateTimeId;
    private String centerOperateDay;
    private LocalTime centerOperateStartTime;
    private LocalTime centerOperateEndTime;
    private String centerOperateUseYn;

    public CenterOperateTimeDto(String centerOperateDay, LocalTime centerOperateStartTime, LocalTime centerOperateEndTime, String centerOperateUseYn) {
        this.centerOperateDay = centerOperateDay;
        this.centerOperateStartTime = centerOperateStartTime;
        this.centerOperateEndTime = centerOperateEndTime;
        this.centerOperateUseYn = centerOperateUseYn;
    }

    public static CenterOperateTimeDto fromEntity(CenterOperateTime entity) {
        return CenterOperateTimeDto.builder()
                .centerOperateTimeId(entity.getId())
                .centerOperateDay(entity.getCenterOperateDay())
                .centerOperateStartTime(entity.getCenterOperateStartTime())
                .centerOperateEndTime(entity.getCenterOperateEndTime())
                .centerOperateUseYn(entity.getCenterOperateUseYn())
                .build();
    }

    public CenterOperateTime toEntity(Center center) {
        CenterOperateTime entity = new CenterOperateTime();
        entity.setId(this.centerOperateTimeId); // 새 엔티티라면 null로 둘 수도 있음
        entity.setCenterOperateDay(this.centerOperateDay);
        entity.setCenterOperateStartTime(this.centerOperateStartTime);
        entity.setCenterOperateEndTime(this.centerOperateEndTime);
        entity.setCenterOperateUseYn(this.centerOperateUseYn);
        entity.setCenter(center); // 연관관계 매핑
        return entity;
    }

}
