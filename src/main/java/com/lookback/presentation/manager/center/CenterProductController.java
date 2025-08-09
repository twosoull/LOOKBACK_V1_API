package com.lookback.presentation.manager.center;

import com.lookback.domain.manager.center.entity.CenterTrainer;
import com.lookback.domain.manager.center.service.CenterProductService;
import com.lookback.domain.manager.center.service.CenterTrainerServiceValidate;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.manager.center.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CenterProductController {

    private final CenterProductService centerProductService;

    /**
     * [PMCP-0001] 센터 관리 > 상품 > 상품 리스트
     * @param pageable
     * @param findCenterProductRequest
     * @param response
     * @return
     * Front 요청 예시
     * const { data } = await useFetch('/api/products', {
     *   query: {
     *     centerId: 1,
     *     centerProductType: '~~~',  // 선택사항, 없으면 전체 조회
     *     page: 0,
     *     size: 20
     *   }
     * })
     */
    @GetMapping("/private/manager/center/product/list")
    public ResponseEntity<ApiResponse> findCenterProducts(
            @PageableDefault(page = 0, size = 20) Pageable pageable,
            @RequestBody FindCenterProductRequest findCenterProductRequest,
                                                   HttpServletResponse response) {

        FindCenterProductResponse findCenterProductResponse = centerProductService.findCenterProducts(findCenterProductRequest, pageable);

        return new ResponseEntity(ApiResponse.success(findCenterProductResponse, response), HttpStatus.OK);
    }

    /**
     * [PMCP-0002] 센터 관리 > 상품 > 상품 추가
     * @param saveCenterProductRequest, response
     * @return
     */
    @PostMapping("/private/manager/center/product/save")
    public ResponseEntity<ApiResponse<T>> saveCenterProduct(@RequestBody SaveCenterProductRequest saveCenterProductRequest,
                                                            HttpServletResponse response) {
        centerProductService.saveCenterProduct(saveCenterProductRequest);
        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    /**
     * [PMCP-0003] 센터 관리 > 상품 > 상품 상세
     * @param findCenterProductDetailRequest
     * @param response
     * @return
     */
    @GetMapping("/private/manager/center/product")
    public ResponseEntity<ApiResponse> findCenterProductDetail(
            @RequestBody FindCenterProductDetailRequest findCenterProductDetailRequest,
            HttpServletResponse response) {

        FindCenterProductDetailResponse findCenterProductDetailResponse = centerProductService.findCenterProductDetail(findCenterProductDetailRequest);

        return new ResponseEntity(ApiResponse.success(findCenterProductDetailResponse, response), HttpStatus.OK);
    }

    /**
     * [PMCP-0004] 센터 관리 > 상품 > 상품 삭제
     * @param removeCenterProductRequest
     * @param response
     * @return
     */
    @PostMapping("/private/manager/center/product/remove")
    public ResponseEntity<ApiResponse> removeCenterProduct(
            @RequestBody RemoveCenterProductRequest removeCenterProductRequest,
            HttpServletResponse response) {

        centerProductService.removeCenterProduct(removeCenterProductRequest);

        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

}
