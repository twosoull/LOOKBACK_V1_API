package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.common.handler.response.ErrorCode;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.repository.CenterRepository;
import com.lookback.presentation.manager.center.dto.CenterDto;
import com.lookback.presentation.manager.center.dto.FindCenterRequest;
import com.lookback.presentation.manager.center.dto.FindCenterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
public class CenterService {

    private final CenterRepository centerRepository;

    public FindCenterResponse findCenter(FindCenterRequest findCenterRequest) {
        Long centerId = findCenterRequest.getCenterId();
        if (centerId == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        Center findCenter = centerRepository.findById(centerId);

        if (findCenter == null) {
            return null;
        }
        return FindCenterResponse.fromEntity(findCenter);
    }
}
