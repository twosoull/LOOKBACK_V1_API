package com.lookback.infrastructure.repositoryCustom;

import com.lookback.domain.record.entity.QRecord;
import com.lookback.domain.record.entity.Record;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
