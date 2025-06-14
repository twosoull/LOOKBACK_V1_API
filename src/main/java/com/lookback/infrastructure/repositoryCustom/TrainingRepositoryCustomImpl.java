package com.lookback.infrastructure.repositoryCustom;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.record.entity.QRecord;
import com.lookback.domain.user.entity.QTraining;
import com.lookback.domain.user.entity.QUsers;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingRepositoryCustomImpl implements TrainingRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public TrainingRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<UserTrainingQueryDto> findTrainingsForTrainerOrderBySortByType(Long trainerId, TrainingStatus trainingStatus, String sortBy) {

        QTraining training = QTraining.training;
        QRecord record = QRecord.record;
        QUsers users = QUsers.users;

        List<UserTrainingQueryDto> latestCreatedAt = jpaQueryFactory.select(
                        Projections.fields(
                                UserTrainingQueryDto.class,
                                users.id,
                                users.userName,
                                users.birthDt,
                                record.recordDate.max().as("latestCreatedAt")
                        )
                )
                .from(training)
                .leftJoin(training.records, record)
                .join(training.student, users)
                .groupBy(users.id, users.userName, users.birthDt)
                .where(training.trainer.id.eq(trainerId)
                        .and(training.trainingStatus.eq(trainingStatus)))
                .orderBy(getOrderSpecifier(sortBy, users, record))
                //sortBy에 따라 두개 중 하나를 사용하고 싶음
                .fetch();

        return latestCreatedAt;

    }
    private OrderSpecifier<?>[] getOrderSpecifier(String sortBy, QUsers users, QRecord record) {
        // 정렬 기준 선택
        if ("userName".equalsIgnoreCase(sortBy)) {
            return new OrderSpecifier[]{
                    users.userName.asc()
                    ,record.recordDate.max().desc().nullsFirst()}; // 이름 오름차순 정렬
        } else {
            return new OrderSpecifier[]{record.recordDate.max().desc().nullsFirst()}; // 기본: 최신 기록 날짜 내림차순(Null 우선)
        }
    }
}
