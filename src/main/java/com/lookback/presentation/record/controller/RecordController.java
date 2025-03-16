package com.lookback.presentation.record.controller;

import com.lookback.domain.record.service.RecordService;
import com.lookback.presentation.common.ApiResponse;
import com.lookback.presentation.record.dto.FindRecordRequest;
import com.lookback.presentation.record.dto.FindRecordResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
