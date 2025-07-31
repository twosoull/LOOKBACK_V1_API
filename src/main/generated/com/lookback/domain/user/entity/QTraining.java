package com.lookback.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTraining is a Querydsl query type for Training
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTraining extends EntityPathBase<Training> {

    private static final long serialVersionUID = -1103378479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTraining training = new QTraining("training");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.lookback.domain.record.entity.Record, com.lookback.domain.record.entity.QRecord> records = this.<com.lookback.domain.record.entity.Record, com.lookback.domain.record.entity.QRecord>createList("records", com.lookback.domain.record.entity.Record.class, com.lookback.domain.record.entity.QRecord.class, PathInits.DIRECT2);

    public final QUsers student;

    public final QTrainer trainer;

    public final EnumPath<com.lookback.domain.common.constant.enums.TrainingStatus> trainingStatus = createEnum("trainingStatus", com.lookback.domain.common.constant.enums.TrainingStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTraining(String variable) {
        this(Training.class, forVariable(variable), INITS);
    }

    public QTraining(Path<? extends Training> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTraining(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTraining(PathMetadata metadata, PathInits inits) {
        this(Training.class, metadata, inits);
    }

    public QTraining(Class<? extends Training> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.student = inits.isInitialized("student") ? new QUsers(forProperty("student"), inits.get("student")) : null;
        this.trainer = inits.isInitialized("trainer") ? new QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

