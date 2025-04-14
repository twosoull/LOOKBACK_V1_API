package com.lookback.presentation.record.controller;

import com.lookback.domain.record.dto.RecordWithDetailsDto;
import com.lookback.domain.record.service.RecordService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.record.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/record/list")
    public ResponseEntity<ApiResponse<T>> findMemberRecords(
            HttpServletRequest request, HttpServletResponse response, FindRecordRequest findRecordRequest) {
        FindRecordResponse membersRecordList = recordService.findMembersRecordList(request, findRecordRequest);
        return new ResponseEntity(ApiResponse.success(membersRecordList, response), HttpStatus.OK);
    }

    @PostMapping("/record/save")
    public ResponseEntity<ApiResponse<T>> saveMemberRecord(
            HttpServletRequest request, HttpServletResponse response, @RequestBody SaveRecordRequest saveRecordRequest
    ) {
        SaveRecordResponse save = recordService.save(request, saveRecordRequest);
        return new ResponseEntity(ApiResponse.success(save, response), HttpStatus.OK);
    }

    @PostMapping("/record/detail/save")
    public ResponseEntity<ApiResponse<T>> saveMemberExerciseRecords(
            @RequestBody SaveExerciseRecordRequest saveExerciseRecordRequest,
            HttpServletResponse response) {
        recordService.saveExerciseRecords(saveExerciseRecordRequest);

        return new ResponseEntity(ApiResponse.success(null, response), HttpStatus.OK);
    }

    @GetMapping("/record/detail")
    public ResponseEntity<ApiResponse<T>> getMemberRecordDetails(
            HttpServletRequest request, HttpServletResponse response,
            FindRecordRequest findRecordRequest
    ){
        FindRecordDetailResponse findRecordDetailResponse = FindRecordDetailResponse.fromDomain(recordService.findRecordDetail(findRecordRequest));
        return new ResponseEntity(ApiResponse.success(findRecordDetailResponse, response), HttpStatus.OK);
    }

}
