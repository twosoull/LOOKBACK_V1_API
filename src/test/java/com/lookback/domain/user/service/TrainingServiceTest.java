package com.lookback.domain.user.service;

import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import com.lookback.presentation.users.dto.SaveTrainingUserRequest;
import com.lookback.presentation.trainer.dto.UpdateTrainingUsersRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Slf4j
class TrainingServiceTest {

    @Autowired
    private TrainingService trainingService;

    @Test
    void findByTrainerIdAndTrainingStatus() {

        FindTrainingUsersRequest findTrainingUsersRequest = new FindTrainingUsersRequest();

        findTrainingUsersRequest.setTrainerId(101L);
        findTrainingUsersRequest.setTrainingStatus("ACTIVE");
        findTrainingUsersRequest.setSortBy("name");

        //List<Users> trainingUsers = trainingService.findAllTrainingUsers(findTrainingUsersRequest);

        FindTrainingUsersRequest findTrainingUsersRequest2 = new FindTrainingUsersRequest();

        findTrainingUsersRequest2.setTrainerId(101L);
        findTrainingUsersRequest2.setTrainingStatus("ACTIVE");
        findTrainingUsersRequest2.setSortBy("recent");

        ///List<Users> trainingUsers2 = trainingService.findAllTrainingUsers(findTrainingUsersRequest2);

    }

    @Test
    void findTrainingUsersByUserName() {
        FindTrainingUsersRequest findTrainingUsersRequest = new FindTrainingUsersRequest();
        findTrainingUsersRequest.setTrainerId(101L);
        findTrainingUsersRequest.setUserName("User F");
        findTrainingUsersRequest.setSortBy("name");

        trainingService.findTrainingUsersByUserName(findTrainingUsersRequest);
    }

    @Test
    @Rollback(false)
    void saveTrainingUser() {
        SaveTrainingUserRequest saveTrainingUserRequest = new SaveTrainingUserRequest();
        saveTrainingUserRequest.setTrainerId(101L);
        saveTrainingUserRequest.setStudentId(201L);
        saveTrainingUserRequest.setTrainingStatus("ACTIVE");

        trainingService.saveTrainingUser(saveTrainingUserRequest);

    }

    @Test
    @Rollback
    void cancelTrainingUser() {
        UpdateTrainingUsersRequest updateTrainingUsersRequest = new UpdateTrainingUsersRequest();
        updateTrainingUsersRequest.setTrainingId(1L);

        trainingService.cancelTraining(updateTrainingUsersRequest);

    }

}