package com.lookback.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecord is a Querydsl query type for Record
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecord extends EntityPathBase<Record> {

    private static final long serialVersionUID = -1244884146L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecord record = new QRecord("record");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> exerciseMinute = createNumber("exerciseMinute", Integer.class);

    public final ListPath<ExerciseRecord, QExerciseRecord> exerciseRecords = this.<ExerciseRecord, QExerciseRecord>createList("exerciseRecords", ExerciseRecord.class, QExerciseRecord.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> recordDate = createDate("recordDate", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> recordTimeEnd = createTime("recordTimeEnd", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> recordTimeStart = createTime("recordTimeStart", java.time.LocalTime.class);

    public final EnumPath<com.lookback.domain.common.constant.enums.ShareStatus> shareStatus = createEnum("shareStatus", com.lookback.domain.common.constant.enums.ShareStatus.class);

    public final com.lookback.domain.user.entity.QTraining training;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.lookback.domain.user.entity.QUsers users;

    public QRecord(String variable) {
        this(Record.class, forVariable(variable), INITS);
    }

    public QRecord(Path<? extends Record> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecord(PathMetadata metadata, PathInits inits) {
        this(Record.class, metadata, inits);
    }

    public QRecord(Class<? extends Record> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.training = inits.isInitialized("training") ? new com.lookback.domain.user.entity.QTraining(forProperty("training"), inits.get("training")) : null;
        this.users = inits.isInitialized("users") ? new com.lookback.domain.user.entity.QUsers(forProperty("users"), inits.get("users")) : null;
    }

}

