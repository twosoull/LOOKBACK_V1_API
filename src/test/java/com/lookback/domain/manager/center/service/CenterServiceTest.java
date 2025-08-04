package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.constant.enums.CenterSnsType;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.repository.CenterRepository;
import com.lookback.presentation.manager.center.dto.CenterFacilityDto;
import com.lookback.presentation.manager.center.dto.CenterOperateTimeDto;
import com.lookback.presentation.manager.center.dto.CenterSnsDto;
import com.lookback.presentation.manager.center.dto.SaveCenterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CenterServiceTest {

    @Mock
    private CenterRepository centerRepository;

    @InjectMocks
    private CenterService centerService;

    @Test
    void 센터_등록_성공() {
        // given
        SaveCenterRequest request = SaveCenterRequest.builder()
                .centerId(null) // 신규 등록
                .centerName("테스트 센터")
                .centerTel("010-1234-5678")
                .centerIntro("소개글")
                .centerTimeCode("BASIC")
                .centerAddress("서울시 강남구")
                .centerPostcode("12345")
                .centerLat("37.123")
                .centerLng("127.456")
                .centerLicenseNumber("123-45-67890")
                .centerMasterName("홍길동")
                .centerShowYn("Y")
                .centerFacilities(List.of(new CenterFacilityDto("주차장")))
                .centerSnss(List.of(new CenterSnsDto(CenterSnsType.INSTAGRAM, "INSTAGRAM", "http://insta.com")))
                .centerOperateTimes(List.of(new CenterOperateTimeDto( "MON", LocalTime.of(9,0), LocalTime.of(21,0), "Y")))
                .build();

        when(centerRepository.findById(null)).thenReturn(null);

        // when
        centerService.saveCenter(request);

        // then
        verify(centerRepository).save(any(Center.class));
    }

    @Test
    void 센터_수정_성공() {
        // given
        Long centerId = 1L;

        Center existing = new Center();
        existing.setId(centerId);
        existing.setCenterName("이전 센터");

        SaveCenterRequest request = SaveCenterRequest.builder()
                .centerId(centerId)
                .centerName("변경된 센터")
                .build();

        when(centerRepository.findById(centerId)).thenReturn(existing);

        // when
        centerService.saveCenter(request);

        // then
        assertEquals("변경된 센터", existing.getCenterName());
        verify(centerRepository, never()).save(any()); // 수정 시 save 호출 안 되는 경우 (JPA 더티 체킹)
    }
}
