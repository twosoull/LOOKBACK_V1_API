package com.lookback.presentation.manager.center;

import com.lookback.domain.manager.center.service.CenterTrainerService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.manager.center.dto.FindCenterTrainerRequest;
import com.lookback.presentation.manager.center.dto.FindCenterTrainerResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CenterTrainerController {

    private final CenterTrainerService centerTrainerService;

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
    public ResponseEntity<ApiResponse> findCenterTrainer(@RequestBody FindCenterTrainerRequest findCenterTrainerRequest,
                                                         @PageableDefault(page = 0, size = 20) Pageable pageable,
                                                         HttpServletResponse response) {

        FindCenterTrainerResponse findCenterProductResponse = centerTrainerService.findCenterTrainer(findCenterTrainerRequest, pageable);

        return new ResponseEntity(ApiResponse.success(findCenterProductResponse, response), HttpStatus.OK);
    }


}
