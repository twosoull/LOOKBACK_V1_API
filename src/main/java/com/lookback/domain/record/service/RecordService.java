package com.lookback.domain.record.service;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.ExerciseRecordFile;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.ExerciseRecordRepository;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.domain.record.repository.RecordShareRepository;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.presentation.record.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final TrainingRepository trainingRepository;
    private final RecordShareRepository recordShareRepository;

    @Transactional
    public RecordCommand.Saved save(RecordCommand.Save save) {
        //TODO 로그인 만들기
        Users users = new Users(1L, "twoowo", "dd", "kakao", "dd", "fasd", "fasd", "asdasf", "asfasd", "fasda", "fasdasf", LocalDateTime.now(),
                LocalDateTime.now(),"dd",LocalDateTime.now()
                );

        Record savedRecord = recordRepository.save(Record.fromCommandSave(save, users));
        return RecordCommand.of(savedRecord);
    }
    /**
     * [회원]
     * 운동 기록 목록(pt와 개인 운동 목록)( 전체, pt, 개인) 카테고리로 나눠진다.
     * */
    @Transactional
    public List<FindRecordResponse> findRecordById(FindRecordRequest findRecordRequest) {
        //TODO 공통에서 userId 가져오기
        //TODO 예외 처리
        Long usersId = 202L;

        String type = findRecordRequest.getType();
        List<Record> findRecords;
        if("pt".equals(type)) {
            findRecords = recordRepository.findByUsersIdAndTrainingIdIsNotNullOrderByCreatedAtDesc(usersId);
        } else if("personal".equals(type)) {
            findRecords = recordRepository.findByUsersIdAndTrainingIdIsNullOrderByCreatedAtDesc(usersId);
        } else {
            findRecords = recordRepository.findByUsersIdOrderByCreatedAtDesc(usersId);
        }

        return FindRecordResponse.getUserRecordsDtosFromEntity(findRecords);
    }

    /**
     * [회원] 기록 상세
     * - 기록 상세의 운동 목록
     * */
    @Transactional
    public FindExerciseRecordResponse findExerciseRecordById(FindExerciseRecordRequest findExerciseRecordRequest) {

        List<ExerciseRecord> exerciseRecords = exerciseRecordRepository.findByIdOrderByOrdAsc(findExerciseRecordRequest.getRecordId());
        Record record = recordRepository.findById(findExerciseRecordRequest.getRecordId());

        return FindExerciseRecordResponse.getExerciseRecordDetailFromEntity(exerciseRecords, record);
    }

    /**
     * [회원] 운동 기록 삭제
     * */
    @Transactional
    public void removeRecordById(RemoveRecordRequest removeRecordRequest) {
        RecordServiceValidator.removeRecordRequestValid(removeRecordRequest);

        Record findRecord = recordRepository.findById(removeRecordRequest.getRecordId());
        try {
            if(findRecord !=null) {
                recordShareRepository.deleteById(findRecord.getRecordShare().getId());
                trainingRepository.deleteById(findRecord.getTraining().getId());
                recordRepository.deleteById(findRecord.getId()); //exerciseRecord, exerciseRecordFile 자동삭제
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

    }


}
