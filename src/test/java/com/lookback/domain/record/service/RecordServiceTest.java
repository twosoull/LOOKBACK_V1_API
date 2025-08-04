package com.lookback.domain.record.service;

import com.lookback.presentation.record.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RecordServiceTest {

    @Autowired
    private RecordService recordService;
/*
    @Test
    void findRecordById(){
        FindRecordRequest findRecordRequest = new FindRecordRequest();
        findRecordRequest.setType("");

        List<FindRecordResponse> findRecord = recordService.findRecordById(findRecordRequest);
        for(FindRecordResponse findRecordResponse: findRecord){
            log.info(findRecordResponse.toString());
        }

        FindRecordRequest findRecordRequest2 = new FindRecordRequest();
        findRecordRequest2.setType("pt");
        List<FindRecordResponse> findRecord2 = recordService.findRecordById(findRecordRequest2);

        for(FindRecordResponse findRecordResponse: findRecord2){
            log.info(findRecordResponse.toString());
        }

        FindRecordRequest findRecordRequest3 = new FindRecordRequest();
        findRecordRequest3.setType("personal");
        List<FindRecordResponse> findRecord3 = recordService.findRecordById(findRecordRequest3);

        for(FindRecordResponse findRecordResponse: findRecord3){
            log.info(findRecordResponse.toString());
        }
    }
*/
    @Test
    void findExerciseRecordById() {
        FindExerciseRecordRequest findExerciseRecordRequest = new FindExerciseRecordRequest();
        findExerciseRecordRequest.setRecordId(2L);

        FindExerciseRecordResponse findExerciseRecordResponse = recordService.findExerciseRecordById(findExerciseRecordRequest);

        log.info(findExerciseRecordResponse.toString());
    }

    @Test
    void removeRecordById() {
        RemoveRecordRequest removeRecordRequest = new RemoveRecordRequest();
        removeRecordRequest.setRecordId(1L);
        recordService.removeRecordById(removeRecordRequest);
    }
}