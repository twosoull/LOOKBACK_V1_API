package com.lookback.domain.manager.center.service;

import com.lookback.domain.manager.center.entity.CenterProduct;
import com.lookback.domain.manager.center.entity.CenterTrainer;
import com.lookback.domain.manager.center.repository.CenterTrainerRepository;
import com.lookback.presentation.manager.center.dto.FindCenterTrainerRequest;
import com.lookback.presentation.manager.center.dto.FindCenterTrainerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterTrainerService {

    private final CenterTrainerRepository centerTrainerRepository;

    public FindCenterTrainerResponse findCenterTrainer(FindCenterTrainerRequest findCenterTrainerRequest, Pageable pageable) {
        CenterTrainerServiceValidate.FindCenterTrainer(findCenterTrainerRequest);

        Page<CenterTrainer> findCenterTrainers = centerTrainerRepository.findByCenterId(findCenterTrainerRequest.getCenterId(), pageable);

        List<CenterTrainer> centerTrainers = findCenterTrainers.getContent();
        int totalCount = findCenterTrainers.getTotalPages();
        int page = findCenterTrainers.getNumber();

        return FindCenterTrainerResponse.fromEntity(findCenterTrainerRequest.getCenterId(), centerTrainers, page, totalCount);

    }
}
