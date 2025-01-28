package com.lookback.domain.user.service;

import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.presentation.users.dto.FindTrainingUsersRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingService {

    private final TrainingRepository trainingRepository;

    @Transactional
    public List<Users> findTrainingUsers(FindTrainingUsersRequest request) {

        List<Training> findTrainingList = trainingRepository
                .findByTrainerIdAndTrainingStatus(request.getTrainingId(),request.getTrainingStatus());

        List<Users> findStudents = findTrainingList.stream()
                .map(Training::getStudent).collect(Collectors.toList());

        return findStudents;
    }

}
