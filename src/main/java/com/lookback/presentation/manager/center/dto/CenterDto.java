package com.lookback.presentation.manager.center.dto;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 센터
 */
@Getter
@Setter
public class CenterDto  {

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


}
