package com.lookback.domain.muscle.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMuscle is a Querydsl query type for Muscle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMuscle extends EntityPathBase<Muscle> {

    private static final long serialVersionUID = -1807628914L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMuscle muscle = new QMuscle("muscle");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    public final NumberPath<Long> activationLevel = createNumber("activationLevel", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath createdBy = createString("createdBy");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath insertion = createString("insertion");

    public final QMuscleGroup muscleGroup;

    public final StringPath muscleName = createString("muscleName");

    public final StringPath origin = createString("origin");

    public final StringPath role = createString("role");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath updatedBy = createString("updatedBy");

    public QMuscle(String variable) {
        this(Muscle.class, forVariable(variable), INITS);
    }

    public QMuscle(Path<? extends Muscle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMuscle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMuscle(PathMetadata metadata, PathInits inits) {
        this(Muscle.class, metadata, inits);
    }

    public QMuscle(Class<? extends Muscle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.muscleGroup = inits.isInitialized("muscleGroup") ? new QMuscleGroup(forProperty("muscleGroup"), inits.get("muscleGroup")) : null;
    }

}

