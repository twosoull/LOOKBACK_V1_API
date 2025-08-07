package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterFacility;
import com.lookback.domain.manager.center.entity.CenterReview;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenterReviewDto {

    private Long centerReviewId;
    private String centerReviewContent;
    private Long centerReviewPoint;
    private String centerReviewProductName;

    public static CenterReviewDto fromEntity(CenterReview entity) {
        return CenterReviewDto.builder()
                .centerReviewId(entity.getId())
                .centerReviewContent(entity.getCenterReviewContent())
                .centerReviewPoint(entity.getCenterReviewPoint())
                .centerReviewProductName(entity.getCenterReviewProductName())
                .build();
    }


}
