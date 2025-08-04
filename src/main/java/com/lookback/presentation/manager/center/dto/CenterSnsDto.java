package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterSnsType;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterSns;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

/**
 * 센터 SNS
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CenterSnsDto {
    private Long centerSnsId;
    private CenterSnsType centerSnsType;
    private String centerSnsName;
    private String centerSnsUrl;

    public CenterSnsDto(CenterSnsType centerSnsType, String centerSnsName, String centerSnsUrl) {
        this.centerSnsType = centerSnsType;
        this.centerSnsName = centerSnsName;
        this.centerSnsUrl = centerSnsUrl;
    }

    public static CenterSnsDto fromEntity(CenterSns entity) {
        return CenterSnsDto.builder()
                .centerSnsId(entity.getId())
                .centerSnsType(entity.getCenterSnsType())
                .centerSnsName(entity.getCenterSnsName())
                .centerSnsUrl(entity.getCenterSnsUrl())
                .build();
    }

    public CenterSns toEntity(Center center) {
        CenterSns entity = new CenterSns();
        entity.setId(this.centerSnsId); // 수정 시 필요
        entity.setCenterSnsType(this.centerSnsType);
        entity.setCenterSnsName(this.centerSnsName);
        entity.setCenterSnsUrl(this.centerSnsUrl);
        entity.setCenter(center); // 연관관계
        return entity;
    }
}
