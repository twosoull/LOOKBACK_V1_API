package com.lookback.infrastructure.repositoryCustom;

import com.lookback.domain.common.constant.enums.MuscleTypeEnum;
import com.lookback.domain.exercise.entity.QExercise;
import com.lookback.domain.file.entity.QUploadFile;
import com.lookback.domain.file.entity.UploadFile;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.muscle.entity.QMuscleCategory;
import com.lookback.domain.muscle.entity.QMuscleGroup;
import com.lookback.domain.record.dto.*;
import com.lookback.domain.record.entity.QExerciseRecord;
import com.lookback.domain.record.entity.QExerciseRecordDetail;
import com.lookback.domain.record.entity.QRecord;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.user.entity.QUsers;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RecordRepositoryCustomImpl implements RecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RecordRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Record> findByUsersIdOrderByRecordDateDesc(Long usersId, String category) {

        QRecord qRecord = QRecord.record;

        BooleanExpression condition = qRecord.users.id.eq(usersId);
        if(category != null && !category.isBlank()) {
            switch (category) {
                case "pt": condition = condition.and(qRecord.training.isNotNull());
                            break;
                case "personal": condition = condition.and(qRecord.training.isNull());
                                 break;
            }
        }

        return queryFactory.select(qRecord)
                            .from(qRecord)
                            .where(condition)
                            .orderBy(qRecord.recordDate.desc())
                            .fetch();
    }

    @Override
    public RecordWithDetailsDto findRecordWithOrderedDetails(Long recordId) {
        QRecord record = QRecord.record;
        QUsers user = QUsers.users;

        RecordWithDetailsDto recordDto = queryFactory
                .select(Projections.constructor(RecordWithDetailsDto.class,
                        record.id,
                        record.training.id,
                        record.recordDate,
                        record.recordTimeStart,
                        record.recordTimeEnd,
                        record.exerciseMinute,
                        record.comment,
                        record.shareStatus,
                        Projections.constructor(UsersDomainDto.class,
                                record.users.nickName,
                                record.users.userName,
                                record.users.profileImageUrl)
                ))
                .from(record)
                .join(record.users, user)
                .where(record.id.eq(recordId))
                .fetchOne();

        QExerciseRecord er = QExerciseRecord.exerciseRecord;
        QMuscleGroup muscleGroup = new QMuscleGroup("mg1");
        QUploadFile uploadFile = QUploadFile.uploadFile;
        List<ExerciseRecordDomainDto> exerciseRecords = queryFactory
                .select(Projections.constructor(ExerciseRecordDomainDto.class,
                        er.id,
                        er.exercise.exerciseName,
                        er.exercise.exerciseType,
                        er.exercise.id,
                        er.memo,
                        er.ord
                ))
                .from(er)
                .where(er.record.id.eq(recordId))
                .orderBy(er.ord.asc())
                .fetch();
        // 2. exerciseId 리스트 뽑기
        List<Long> exerciseIds = exerciseRecords.stream()
                .map(ExerciseRecordDomainDto::getExerciseId)
                .distinct()
                .toList();

        //근육 그룹 찾기
        List<MuscleGroup> muscleGroups = queryFactory
                .selectFrom(muscleGroup)
                .join(muscleGroup.muscleCategory).fetchJoin()
                .where(muscleGroup.exercise.id.in(exerciseIds))
                .fetch();

        //미디어 찾기
        List<UploadFileDomainDto> uploadFiles = queryFactory.select(
                Projections.constructor(UploadFileDomainDto.class,
                        uploadFile.uuid,
                        uploadFile.type,
                        uploadFile.referenceId,
                        uploadFile.fullPath,
                        uploadFile.extension,
                        uploadFile.originalName,
                        uploadFile.fileName,
                        uploadFile.relativePath,
                        uploadFile.ord,
                        uploadFile.size,
                        uploadFile.status)
                ).from(uploadFile)
                .where(uploadFile.referenceId.in(exerciseIds))
                .orderBy(uploadFile.ord.asc())
                .fetch();

        //미디어 넣기
        uploadFiles.stream().forEach(uf -> {
            for (ExerciseRecordDomainDto dto : exerciseRecords) {
                if(dto.getExerciseId().equals(uf.getReferenceId())) {
                    dto.getUploadFileDomainDto().add(uf);
                }
            }
        });

        // 4. Map으로 정리
        Map<Long, String> firstAgonistNameMap = new HashMap<>();
        Map<Long, String> firstSynergistNameMap = new HashMap<>();

        for (MuscleGroup group : muscleGroups) {
            Long eid = group.getExercise().getId();
            if (group.getMuscleType() == MuscleTypeEnum.AGONIST && !firstAgonistNameMap.containsKey(eid)) {
                firstAgonistNameMap.put(eid, group.getMuscleCategory().getMuscleName());
            } else if (group.getMuscleType() == MuscleTypeEnum.SYNERGIST && !firstSynergistNameMap.containsKey(eid)) {
                firstSynergistNameMap.put(eid, group.getMuscleCategory().getMuscleName());
            }
        }

        // 5. 매핑
        exerciseRecords.forEach(dto -> {
            dto.setAgonistMuscleName(firstAgonistNameMap.get(dto.getExerciseId()));
            dto.setSynergistMuscleName(firstSynergistNameMap.get(dto.getExerciseId()));
        });

        QExerciseRecordDetail erd = QExerciseRecordDetail.exerciseRecordDetail;

        List<ExerciseRecordDetailDomainDto> allDetails = queryFactory
                .select(Projections.constructor(ExerciseRecordDetailDomainDto.class,
                        erd.id,
                        erd.exerciseRecord.id.as("exerciseRecordId"),
                        erd.ord,
                        erd.repsPerSet,
                        erd.weight,
                        erd.duration,
                        erd.memo,
                        erd.type
                ))
                .from(erd)
                .where(erd.exerciseRecord.record.id.eq(recordId))
                .orderBy(erd.exerciseRecord.ord.asc(), erd.ord.asc())
                .fetch();


        // details를 exerciseRecordId 기준으로 그룹핑
        Map<Long, List<ExerciseRecordDetailDomainDto>> detailMap = allDetails.stream()
                .collect(Collectors.groupingBy(ExerciseRecordDetailDomainDto::getExerciseRecordId));

        // exerciseRecord에 detail을 넣어주기
        for (ExerciseRecordDomainDto erDto : exerciseRecords) {
            List<ExerciseRecordDetailDomainDto> details = detailMap.get(erDto.getExerciseRecordId());
            erDto.setExerciseRecordDetails(details != null ? details : new ArrayList<>());
        }

        // 최종적으로 recordDto에 담기
        recordDto.setExerciseRecords(exerciseRecords);

        return recordDto;
    }
}
