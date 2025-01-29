package com.lookback.domain.record.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecordServiceTest {

    @Autowired
    private RecordService recordService;

    @Test
    void findRecordById(){
        recordService.findRecordById();
    }

}