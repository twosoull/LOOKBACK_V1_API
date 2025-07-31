package com.lookback.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseRecordDetail is a Querydsl query type for ExerciseRecordDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseRecordDetail extends EntityPathBase<ExerciseRecordDetail> {

    private static final long serialVersionUID = 1921067575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseRecordDetail exerciseRecordDetail = new QExerciseRecordDetail("exerciseRecordDetail");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> duration = createNumber("duration", Integer.class);

    public final QExerciseRecord exerciseRecord;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Long> ord = createNumber("ord", Long.class);

    public final NumberPath<Integer> repsPerSet = createNumber("repsPerSet", Integer.class);

    public final EnumPath<com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum> type = createEnum("type", com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QExerciseRecordDetail(String variable) {
        this(ExerciseRecordDetail.class, forVariable(variable), INITS);
    }

    public QExerciseRecordDetail(Path<? extends ExerciseRecordDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseRecordDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseRecordDetail(PathMetadata metadata, PathInits inits) {
        this(ExerciseRecordDetail.class, metadata, inits);
    }

    public QExerciseRecordDetail(Class<? extends ExerciseRecordDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseRecord = inits.isInitialized("exerciseRecord") ? new QExerciseRecord(forProperty("exerciseRecord"), inits.get("exerciseRecord")) : null;
    }

}

