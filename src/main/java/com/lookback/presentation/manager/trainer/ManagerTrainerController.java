package com.lookback.presentation.manager.trainer;

import com.lookback.domain.user.service.TrainerService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.manager.trainer.dto.FindTrainerBasicInfoResponse;
import com.lookback.presentation.manager.trainer.dto.FindTrainerDetailResponse;
import com.lookback.presentation.manager.trainer.dto.SaveTrainerProfileRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerTrainerController {

    private final TrainerService trainerService;

    /**
     * [PMT-0001] 마이페이지 > 트레이너 > 기본정보
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/private/manager/trainer/basicInfo")
    public ResponseEntity<ApiResponse<T>> findTrainerBasicInfo(
            HttpServletRequest request, HttpServletResponse response) {
        FindTrainerBasicInfoResponse findTrainerBasicInfoResponse = trainerService.findTrainerBasicInfo(request);
        return new ResponseEntity(ApiResponse.success(findTrainerBasicInfoResponse, response), HttpStatus.OK);
    }

    /**
     * [PMT-0002] 마이페이지 > 트레이너 > 내 프로필
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/private/manager/trainer/detail")
    public ResponseEntity<ApiResponse<T>> findTrainerDetail(
            HttpServletRequest request, HttpServletResponse response) {
        FindTrainerDetailResponse findTrainerDetailResponse = trainerService.findTrainerDetail(request);
        return new ResponseEntity(ApiResponse.success(findTrainerDetailResponse, response), HttpStatus.OK);
    }

    /**
     * [PMT-0003] 마이페이지 > 트레이너 > 프로필 저장
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/private/manager/trainer/save")
    public ResponseEntity<ApiResponse<T>> saveTrainerProfile(
            SaveTrainerProfileRequest saveTrainerProfileRequest,
            HttpServletRequest request, HttpServletResponse response) {
        trainerService.saveTrainerProfile(request, saveTrainerProfileRequest);
        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }
}
