package com.lookback.domain.muscle.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMuscleGroup is a Querydsl query type for MuscleGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMuscleGroup extends EntityPathBase<MuscleGroup> {

    private static final long serialVersionUID = 226462097L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMuscleGroup muscleGroup = new QMuscleGroup("muscleGroup");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath createdBy = createString("createdBy");

    public final com.lookback.domain.exercise.entity.QExercise exercise;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMuscleCategory muscleCategory;

    public final EnumPath<com.lookback.domain.common.constant.enums.MuscleTypeEnum> muscleType = createEnum("muscleType", com.lookback.domain.common.constant.enums.MuscleTypeEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath updatedBy = createString("updatedBy");

    public QMuscleGroup(String variable) {
        this(MuscleGroup.class, forVariable(variable), INITS);
    }

    public QMuscleGroup(Path<? extends MuscleGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMuscleGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMuscleGroup(PathMetadata metadata, PathInits inits) {
        this(MuscleGroup.class, metadata, inits);
    }

    public QMuscleGroup(Class<? extends MuscleGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exercise = inits.isInitialized("exercise") ? new com.lookback.domain.exercise.entity.QExercise(forProperty("exercise"), inits.get("exercise")) : null;
        this.muscleCategory = inits.isInitialized("muscleCategory") ? new QMuscleCategory(forProperty("muscleCategory"), inits.get("muscleCategory")) : null;
    }

}

