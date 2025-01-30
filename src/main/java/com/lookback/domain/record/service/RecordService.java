package com.lookback.domain.record.service;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.muscle.dto.MuscleCategoryDto;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import com.lookback.presentation.record.dto.FindRecordRequest;
import com.lookback.presentation.record.dto.FindRecordResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.lookback.presentation.record.dto.FindRecordResponse.getUserRecordsDtosFromEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordCommand.Saved save(RecordCommand.Save save) {
        //TODO 로그인 만들기
        Users users = new Users(1L, "twoowo", "dd", "kakao", "dd", "fasd", "fasd", "asdasf", "asfasd", "fasda", "fasdasf", LocalDateTime.now(),
                LocalDateTime.now(),"dd",LocalDateTime.now()
                );

        Record savedRecord = recordRepository.save(Record.fromCommandSave(save, users));
        return RecordCommand.of(savedRecord);
    }
    /**
     * [사용자]
     * 운동 기록 목록(pt와 개인 운동 목록)( 전체, pt, 개인) 카테고리로 나눠진다.
     * */
    @Transactional
    public List<FindRecordResponse> findRecordById(FindRecordRequest findRecordRequest) {
        //TODO 공통에서 userId 가져오기
        //TODO 예외 처리
        Long usersId = 202L;

        String type              = findRecordRequest.getType();
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



}
