package com.lookback.domain.record.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.exercise.repository.ExerciseRepository;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.ExerciseRecordRepository;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.presentation.record.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final MuscleGroupRepository muscleGroupRepository;

    @Transactional
    public RecordCommand.Saved save(RecordCommand.Save save) {
        //TODO 로그인 만들기
        Users users = new Users(1L, "twoowo", "dd", "kakao", "dd", "fasd", "fasd", "asdasf", "asfasd", "fasda", "fasdasf", LocalDateTime.now(),
                LocalDateTime.now(),"dd",LocalDateTime.now()
                );

        Record savedRecord = recordRepository.save(Record.fromCommandSave(save, users));
        return RecordCommand.of(savedRecord);
    }
    /**`
     * [회원]
     * 운동 기록 목록(pt와 개인 운동 목록)( 전체, pt, 개인) 카테고리로 나눠진다.
     * */
    @Transactional
    public List<FindRecordResponse> findRecordById(HttpServletRequest request, FindRecordRequest findRecordRequest) {
        //TODO 공통에서 userId 가져오기
        //TODO 예외 처리
        if(findRecordRequest == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        Long usersId = UserContext.getUser(request).getId();
        if(findRecordRequest.getUserType() != null) {
            if(findRecordRequest.getUserType().equals("TRAINER")) {
                usersId = findRecordRequest.getUserId();
            }
        }

        String category = findRecordRequest.getType();
        List<Record> findRecords = null;

        List<Record> records = recordRepository.findByUsersId(usersId, category);
        for(Record r : records) {
            List<ExerciseRecord> findExRecordOrdAsc = exerciseRecordRepository.findByIdOrderByOrdAsc(r.getId());
            List<Long> exerciseIds = new ArrayList<>();
            for(ExerciseRecord er : findExRecordOrdAsc) {
                exerciseIds.add(er.getExercise().getId());
            }
            //muscleGroupRepository.findMuscleCategoryByExcercise(exerciseIds);

                //머슬카테고리 찾기
                //exerciseRepository.findByIdIn(exerciseId);
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
                trainingRepository.deleteById(findRecord.getTraining().getId());
                recordRepository.deleteById(findRecord.getId()); //exerciseRecord, exerciseRecordFile 자동삭제
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

    }


}
