package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterReview;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class FindCenterResponse {

    private Long centerId;
    private String centerName;          // 센터_이름
    private String centerTel;           // 센처_전화번호
    private String centerIntro;         // 센터_소개
    private String centerTimeCode;      // 센터_운영시간_유형
    private String centerAddress;       // 센터_주소
    private String centerAddressDetail; // 센터_상세_주소
    private String centerPostcode;      // 센터_우편번호
    private String centerLat;           // 센터_위도
    private String centerLng;           // 센터_경도
    private String centerLicenseNumber; // 센터_사업자번호
    private String centerMasterName;    // 센터_대표자_이름
    private String centerShowYn;        // 센터_공개_yn
    private double centerReviewSumPoint; // 센터의 별점

    private List<CenterOperateTimeDto> centerOperateTimes = new ArrayList<>();
    private List<CenterFacilityDto> centerFacilities = new ArrayList<>();
    private List<CenterSnsDto> centerSnss = new ArrayList<>();
    private List<CenterReviewDto> centerReviews = new ArrayList<>();

    // TODO 사진 추가

    public static FindCenterResponse fromEntity(Center center) {
        return FindCenterResponse.builder()
                .centerId(center.getId())
                .centerName(center.getCenterName())
                .centerTel(center.getCenterTel())
                .centerIntro(center.getCenterIntro())
                .centerTimeCode(center.getCenterTimeCode())
                .centerAddress(center.getCenterAddress())
                .centerAddressDetail(center.getCenterAddressDetail())
                .centerPostcode(center.getCenterPostcode())
                .centerLat(center.getCenterLat())
                .centerLng(center.getCenterLng())
                .centerLicenseNumber(center.getCenterLicenseNumber())
                .centerMasterName(center.getCenterMasterName())
                .centerShowYn("Y") // 또는 center.getCenterShowYn() 이 존재할 경우 사용
                .centerOperateTimes(
                        center.getCenterOperateTimes().stream()
                                .map(CenterOperateTimeDto::fromEntity)
                                .collect(Collectors.toList())
                )
                .centerFacilities(
                        center.getCenterFacilities().stream()
                                .map(CenterFacilityDto::fromEntity)
                                .collect(Collectors.toList())
                )
                .centerSnss(
                        center.getCenterSnss().stream()
                                .map(CenterSnsDto::fromEntity)
                                .collect(Collectors.toList())
                )
                .centerReviews(
                        center.getCenterReviews().stream()
                                .map(CenterReviewDto::fromEntity)
                                .collect(Collectors.toList())
                )
                .centerReviewSumPoint(center.getCenterReviews().stream()
                        .mapToLong(CenterReview::getCenterReviewPoint)
                        .average()
                        .orElse(0.0))
                .build();
    }
}
