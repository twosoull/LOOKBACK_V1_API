package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.domain.common.constant.enums.CenterSnsType;
import com.lookback.domain.manager.center.entity.CenterSns;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 센터 SNS
 */
@Setter
@Getter
@Builder
public class CenterSnsDto {
    private Long centerSnsId;
    private CenterSnsType centerSnsType;
    private String centerSnsName;
    private String centerSnsUrl;

    public static CenterSnsDto fromEntity(CenterSns entity) {
        return CenterSnsDto.builder()
                .centerSnsId(entity.getId())
                .centerSnsType(entity.getCenterSnsType())
                .centerSnsName(entity.getCenterSnsName())
                .centerSnsUrl(entity.getCenterSnsUrl())
                .build();
    }
}
