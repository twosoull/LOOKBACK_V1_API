package com.lookback.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseRecord is a Querydsl query type for ExerciseRecord
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseRecord extends EntityPathBase<ExerciseRecord> {

    private static final long serialVersionUID = 1536913990L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseRecord exerciseRecord = new QExerciseRecord("exerciseRecord");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final com.lookback.domain.exercise.entity.QExercise exercise;

    public final ListPath<ExerciseRecordDetail, QExerciseRecordDetail> exerciseRecordDetails = this.<ExerciseRecordDetail, QExerciseRecordDetail>createList("exerciseRecordDetails", ExerciseRecordDetail.class, QExerciseRecordDetail.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> ord = createNumber("ord", Integer.class);

    public final QRecord record;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QExerciseRecord(String variable) {
        this(ExerciseRecord.class, forVariable(variable), INITS);
    }

    public QExerciseRecord(Path<? extends ExerciseRecord> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseRecord(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseRecord(PathMetadata metadata, PathInits inits) {
        this(ExerciseRecord.class, metadata, inits);
    }

    public QExerciseRecord(Class<? extends ExerciseRecord> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exercise = inits.isInitialized("exercise") ? new com.lookback.domain.exercise.entity.QExercise(forProperty("exercise"), inits.get("exercise")) : null;
        this.record = inits.isInitialized("record") ? new QRecord(forProperty("record"), inits.get("record")) : null;
    }

}

