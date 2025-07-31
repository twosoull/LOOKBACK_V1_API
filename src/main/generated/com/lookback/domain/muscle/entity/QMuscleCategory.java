package com.lookback.domain.muscle.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMuscleCategory is a Querydsl query type for MuscleCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMuscleCategory extends EntityPathBase<MuscleCategory> {

    private static final long serialVersionUID = -1317046100L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMuscleCategory muscleCategory = new QMuscleCategory("muscleCategory");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath createdBy = createString("createdBy");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath muscleCategoryName = createString("muscleCategoryName");

    public final StringPath muscleName = createString("muscleName");

    public final QMuscleCategory parent;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath updatedBy = createString("updatedBy");

    public QMuscleCategory(String variable) {
        this(MuscleCategory.class, forVariable(variable), INITS);
    }

    public QMuscleCategory(Path<? extends MuscleCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMuscleCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMuscleCategory(PathMetadata metadata, PathInits inits) {
        this(MuscleCategory.class, metadata, inits);
    }

    public QMuscleCategory(Class<? extends MuscleCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QMuscleCategory(forProperty("parent"), inits.get("parent")) : null;
    }

}

