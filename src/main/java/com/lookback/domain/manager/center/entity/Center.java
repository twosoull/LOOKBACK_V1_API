package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import com.lookback.presentation.manager.center.dto.SaveCenterRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 센터
 */
@Entity
@Getter
@Setter
public class Center extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="CENTER_ID")
    private Long id;
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
    private String centerShowYn;        // 센터_공개_여부

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CenterSns> centerSnss = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CenterFacility> centerFacilities = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CenterOperateTime> centerOperateTimes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CenterProduct> centerProducts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CenterReview> centerReviews = new ArrayList<>();

    // review 및 product 제외, center profile 관련만
    public void update(SaveCenterRequest dto) {
        this.centerName = dto.getCenterName();
        this.centerTel = dto.getCenterTel();
        this.centerIntro = dto.getCenterIntro();
        this.centerTimeCode = dto.getCenterTimeCode();
        this.centerAddress = dto.getCenterAddress();
        this.centerAddressDetail = dto.getCenterAddressDetail();
        this.centerPostcode = dto.getCenterPostcode();
        this.centerLat = dto.getCenterLat();
        this.centerLng = dto.getCenterLng();
        this.centerLicenseNumber = dto.getCenterLicenseNumber();
        this.centerMasterName = dto.getCenterMasterName();
        this.centerShowYn = dto.getCenterShowYn();

        // centerOperateTimes
        if (dto.getCenterOperateTimes() != null) {
            this.centerOperateTimes.clear();
            dto.getCenterOperateTimes().forEach(operateDto ->
                    this.centerOperateTimes.add(operateDto.toEntity(this))
            );
        }

        // centerFacilities
        if (dto.getCenterFacilities() != null) {
            this.centerFacilities.clear();
            dto.getCenterFacilities().forEach(facilityDto ->
                    this.centerFacilities.add(facilityDto.toEntity(this))
            );
        }

        // centerSnss
        if (dto.getCenterSnss() != null) {
            this.centerSnss.clear();
            dto.getCenterSnss().forEach(snsDto ->
                    this.centerSnss.add(snsDto.toEntity(this))
            );
        }
    }
}
