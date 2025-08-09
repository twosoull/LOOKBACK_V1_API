package com.lookback.presentation.manager.center;

import com.lookback.domain.manager.center.service.CenterProductService;
import com.lookback.domain.manager.center.service.CenterTrainerService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.manager.center.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CenterTrainerController {

    private final CenterTrainerService centerTrainerService;
    private final CenterProductService centerProductService;

    /**
     * [PMCT-0001] 센터 관리 > 트레이너 > 트레이너 목록
     * @param findCenterTrainerRequest
     * @param pageable
     * @param response
     * @return
     * Front 요청 예시
     * const { data } = await useFetch('/api/private/manager/center/trainer/list', {
     *   query: {
     *     centerId: 1,
     *     page: 0,
     *     size: 20
     *   }
     * })
     */
    @GetMapping("/private/manager/center/trainer/list")
    public ResponseEntity<ApiResponse> findCenterTrainerList(@RequestBody FindCenterTrainerRequest findCenterTrainerRequest,
                                                         @PageableDefault(page = 0, size = 20) Pageable pageable,
                                                         HttpServletResponse response) {

        FindCenterTrainerResponse findCenterProductResponse = centerTrainerService.findCenterTrainers(findCenterTrainerRequest, pageable);

        return new ResponseEntity(ApiResponse.success(findCenterProductResponse, response), HttpStatus.OK);
    }

    /**
     *  [PMCT-0002] 센터 관리 > 트레이너 > 트레이너 상세
     * @param findCenterTrainerRequest
     * @param response
     * @return
     */
    @GetMapping("/private/manager/center/trainer/detail")
    public ResponseEntity<ApiResponse> findCenterTrainer(@RequestBody FindCenterTrainerDetailRequest findCenterTrainerRequest,
                                                         HttpServletResponse response) {

        FindCenterTrainerDetailResponse findCenterTrainerDetailResponse = centerTrainerService.findCenterTrainer(findCenterTrainerRequest);

        return new ResponseEntity(ApiResponse.success(findCenterTrainerDetailResponse, response), HttpStatus.OK);
    }

    /**
     * [PMCT-0003] 센터 관리 > 트레이너 > 트레이너 수락 & 거절
     */
    @PostMapping("/private/manager/center/trainer/status/update")
    public ResponseEntity<ApiResponse> updateCenterTrainerStatus(@RequestBody UpdateCenterTrainerStatusRequest updateCenterTrainerStatusRequest,
                                                         HttpServletResponse response) {

        centerTrainerService.updateCenterTrainerStatus(updateCenterTrainerStatusRequest);

        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    /**
     * [PMCT-0004] 센터 관리 > 트레이너 > 트레이너 제외
     */
    @PostMapping("/private/manager/center/trainer/status/update/exclusion")
    public ResponseEntity<ApiResponse> updateCenterTrainerStatusExclusion(@RequestBody UpdateCenterTrainerStatusExclusionRequest updateCenterTrainerStatusExclusionRequest,
                                                                 HttpServletResponse response) {

        centerTrainerService.updateCenterTrainerStatusExclusion(updateCenterTrainerStatusExclusionRequest);

        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    /**
     * [PMCT-0005] 센터 관리 > 트레이너 > 최고관리자 임명
     */
    @PostMapping("/private/manager/center/trainer/role/update/super")
    public ResponseEntity<ApiResponse> updateCenterTrainerRoleSuper(HttpServletRequest request
            , @RequestBody UpdateCenterTrainerRoleSuperRequest updateCenterTrainerRoleSuperRequest,
                                                                    HttpServletResponse response) {

        centerTrainerService.updateCenterTrainerRoleSuper(request,updateCenterTrainerRoleSuperRequest);

        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    /**
     * [PMCT-0006] 센터 관리 > 트레이너 > 일반 권한 처리
     */
    @PostMapping("/private/manager/center/trainer/role/update/general")
    public ResponseEntity<ApiResponse> updateCenterTrainerRoleGeneral(HttpServletRequest request
            , @RequestBody UpdateCenterTrainerRoleGeneralRequest updateCenterTrainerRoleGeneralRequest,
                                                                    HttpServletResponse response) {

        centerTrainerService.updateCenterTrainerRoleGeneral(request,updateCenterTrainerRoleGeneralRequest);

        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    /**
     * [PMCT-0007] 센터 관리 > 트레이너 > 상품 목록
     */
    @GetMapping("/private/manager/center/trainer/product/list")
    public ResponseEntity<ApiResponse> findCenterTrainerProducts(HttpServletRequest request
            , @RequestBody FindCenterTrainerProductRequest findCenterTrainerProductRequest,
                                                                 @PageableDefault(page = 0, size = 20) Pageable pageable,
                                                                      HttpServletResponse response) {

        FindCenterTrainerProductResponse findCenterTrainerProductResponse = centerProductService
                .findCenterTrainerProducts(request,findCenterTrainerProductRequest, pageable);

        return new ResponseEntity(ApiResponse.success(findCenterTrainerProductResponse, response), HttpStatus.OK);
    }


}
