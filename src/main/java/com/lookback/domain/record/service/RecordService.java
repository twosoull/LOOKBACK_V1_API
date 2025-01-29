package com.lookback.domain.record.service;

import com.lookback.domain.record.command.RecordCommand;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.domain.user.entity.Users;
import com.lookback.presentation.record.dto.FindRecordRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional
    public void findRecordById() {
        //TODO 공통에서 userId 가져오기
        Long usersId = 201L;

        List<Record> findRecords = recordRepository.findByUsersIdOrderByCreatedAtDesc(usersId);
        log.info(findRecords.get(0).getExerciseRecords().get(0).getExercise().getMuscleGroups().get(0).getMuscleCategory().getMuscleCategoryName());

        log.debug(findRecords.toString());
    }

}
