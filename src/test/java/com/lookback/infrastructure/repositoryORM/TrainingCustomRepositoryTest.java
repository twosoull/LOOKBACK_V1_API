package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.record.entity.QRecord;
import com.lookback.domain.user.entity.QTraining;
import com.lookback.domain.user.entity.QUsers;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.lookback.domain.record.entity.QRecord.record;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class TrainingCustomRepositoryTest {

    @Autowired
    EntityManager em;


    @Test
    void findTrainingsForTrainerOrderBySortByType(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QTraining training = QTraining.training;
        QRecord record = QRecord.record;
        QUsers users = QUsers.users;
        List<Tuple> fetch1 = jpaQueryFactory.select(users.id, users.userName, users.birthDt, record.recordDate.max())
                .from(training)
                .leftJoin(training.records, record)
                .join(training.student, users)
                .groupBy(users.id, users.userName, users.birthDt)
                .where(training.trainer.id.eq(1L)
                        .and(training.trainingStatus.eq(TrainingStatus.IN_PROGRESS)))
                .orderBy(users.userName.asc(), record.recordDate.max().desc().nullsFirst())
                .fetch();

        log.info("11");

        List<Tuple> fetch2 = jpaQueryFactory.select(users.id, users.userName, users.birthDt, record.recordDate.max())
                .from(training)
                .leftJoin(training.records, record)
                .join(training.student, users)
                .groupBy(users.id, users.userName, users.birthDt)
                .where(training.trainer.id.eq(1L)
                        .and(training.trainingStatus.eq(TrainingStatus.IN_PROGRESS)))
                .orderBy(record.recordDate.max().desc().nullsFirst())
                .fetch();

        log.info("22");
    }

}