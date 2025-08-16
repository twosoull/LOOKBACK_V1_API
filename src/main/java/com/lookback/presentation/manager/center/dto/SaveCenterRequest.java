package com.lookback.presentation.manager.center.dto;

import com.lookback.domain.manager.center.entity.Center;
import com.lookback.presentation.record.dto.UploadFileDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveCenterRequest {

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

    private List<CenterOperateTimeDto> centerOperateTimes = new ArrayList<>();
    private List<CenterFacilityDto> centerFacilities = new ArrayList<>();
    private List<CenterSnsDto> centerSnss = new ArrayList<>();
    private List<UploadFileDto> uploadFiles = new ArrayList<>();
    private List<UploadFileDto> delFiles = new ArrayList<>();

    public Center toEntity(Center center) {
        center.setId(this.getCenterId()); // 새로 만드는 경우 null로 둘 수도 있음
        center.setCenterName(this.getCenterName());
        center.setCenterTel(this.getCenterTel());
        center.setCenterIntro(this.getCenterIntro());
        center.setCenterTimeCode(this.getCenterTimeCode());
        center.setCenterAddress(this.getCenterAddress());
        center.setCenterAddressDetail(this.getCenterAddressDetail());
        center.setCenterPostcode(this.getCenterPostcode());
        center.setCenterLat(this.getCenterLat());
        center.setCenterLng(this.getCenterLng());
        center.setCenterLicenseNumber(this.getCenterLicenseNumber());
        center.setCenterMasterName(this.getCenterMasterName());
        center.setCenterShowYn(this.getCenterShowYn());

        return center;
    }
}
