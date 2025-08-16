package com.lookback.domain.manager.center.service;

import com.lookback.domain.common.constant.enums.FileType;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode;
import com.lookback.domain.common.handler.response.ErrorCode;
import com.lookback.domain.file.service.FileService;
import com.lookback.domain.manager.center.entity.Center;
import com.lookback.domain.manager.center.entity.CenterFacility;
import com.lookback.domain.manager.center.entity.CenterOperateTime;
import com.lookback.domain.manager.center.entity.CenterSns;
import com.lookback.domain.manager.center.repository.CenterRepository;
import com.lookback.presentation.manager.center.dto.*;
import com.lookback.presentation.record.dto.UploadFileDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.INVALID_PARAMETER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CenterService {

    private final CenterRepository centerRepository;
    private final FileService fileService;

    public FindCenterResponse findCenter(FindCenterRequest findCenterRequest) {
        Long centerId = findCenterRequest.getCenterId();
        if (centerId == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }

        Center findCenter = centerRepository.findById(centerId);

        // TODO 사진 추가
        if (findCenter == null) {
            return null;
        }

        return FindCenterResponse.fromEntity(findCenter);
    }

    @Transactional
    public void saveCenter(SaveCenterRequest saveCenterRequest) {

        // 등록
        boolean isCreate = saveCenterRequest.getCenterId() == null;
        boolean isUpdate = !isCreate;
        Long centerId = null;
        if (isCreate) {
            Center center = saveCenterRequest.toEntity(new Center());
            List<CenterFacility> centerFacilities = saveCenterRequest.getCenterFacilities().stream().map(centerFacilityDto -> centerFacilityDto.toEntity(center)).toList();
            List<CenterSns> centerSnss = saveCenterRequest.getCenterSnss().stream().map(centerSnsDto -> centerSnsDto.toEntity(center)).toList();
            List<CenterOperateTime> centerOperateTimes = saveCenterRequest.getCenterOperateTimes().stream().map(centerOperateTimeDto -> centerOperateTimeDto.toEntity(center)).toList();
            center.setCenterFacilities(centerFacilities);
            center.setCenterSnss(centerSnss);
            center.setCenterOperateTimes(centerOperateTimes);

            Center saveCenter = centerRepository.save(center);
            centerId = saveCenter.getId();
        }

        if (isUpdate) {
            Center findCenter = centerRepository.findById(saveCenterRequest.getCenterId());
            if (findCenter == null) {
                throw new RestApiException(INVALID_PARAMETER);
            }

            findCenter.update(saveCenterRequest);
            centerId = findCenter.getId();
        }

        // TODO FILE 예시로 사용
        try {
            List<UploadFileDto> uploadFileDtos = saveCenterRequest.getUploadFiles();
            fileService.linkToReferenceIdList(uploadFileDtos, centerId, null);

            // del도 만들기
            fileService.deleteToRefernceIdList(saveCenterRequest.getDelFiles());
        } catch (Exception e) {
            throw new RestApiException(CommonErrorCode.FILE_UPLOAD_FAIL);
        }

    }

    public void updateCenterShowYn(UpdateCenterShowYnRequest updateCenterShowYnRequest) {
        Center findCenter = centerRepository.findById(updateCenterShowYnRequest.getCenterId());
        if (findCenter == null) {
            throw new RestApiException(INVALID_PARAMETER);
        }
        findCenter.setCenterShowYn(updateCenterShowYnRequest.getCenterShowYn());
    }
}
