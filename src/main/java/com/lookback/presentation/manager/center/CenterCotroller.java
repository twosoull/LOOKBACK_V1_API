package com.lookback.presentation.manager.center;

import com.lookback.domain.manager.center.service.CenterService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.manager.center.dto.CenterDto;
import com.lookback.presentation.manager.center.dto.FindCenterRequest;
import com.lookback.presentation.manager.center.dto.SaveCenterRequest;
import com.lookback.presentation.manager.center.dto.UpdateCenterShowYnRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CenterCotroller {

    private final CenterService centerService;

    /**
     * [PMC-0001] 센터 관리 > 프로필 수정 > init 화면
     * @param findCenterRequest, response
     * @return
     */
    @GetMapping("/private/manager/center/profile")
    public ResponseEntity<ApiResponse<T>> findCenter(@RequestBody FindCenterRequest findCenterRequest,
                                                     HttpServletResponse response) {

        return new ResponseEntity(ApiResponse.success(centerService.findCenter(findCenterRequest), response), HttpStatus.OK);
    }

    /**
     * [PMC-0002] 센터 관리 > 프로필 수정 > 등록 & 수정
     * @param saveCenterRequest, response
     * @return
     */
    @PostMapping("/private/manager/center/profile/save")
    public ResponseEntity<ApiResponse<T>> saveCenter(@RequestBody SaveCenterRequest saveCenterRequest,
                                                     HttpServletResponse response) {

        centerService.saveCenter(saveCenterRequest);
        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    /**
     * [PMC-0003] 센터 관리 > 프로필 > 센터 프로필 공개 수정
     * @param updateCenterShowYnRequest response
     * @return
     */
    @PostMapping("/private/manager/center/profile/update/centerShowYn")
    public ResponseEntity<ApiResponse<T>> updateCenterShowYn(@RequestBody UpdateCenterShowYnRequest updateCenterShowYnRequest,
                                                             HttpServletResponse response) {

        centerService.updateCenterShowYn(updateCenterShowYnRequest);
        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }


}
