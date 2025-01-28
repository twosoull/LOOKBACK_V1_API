package com.lookback.domain.user.service;

import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TrainingServiceTest {

    @Autowired
    private TrainingService trainingService;

    @Test
    void findByTrainerIdAndTrainingStatus() {

        FindTrainingUsersRequest findTrainingUsersRequest = new FindTrainingUsersRequest();

        findTrainingUsersRequest.setTrainingId(101L);
        findTrainingUsersRequest.setTrainingStatus("ACTIVE");

        List<Users> trainingUsers = trainingService.findTrainingUsers(findTrainingUsersRequest);

    }
}