package com.lookback.domain.exercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseVideo is a Querydsl query type for ExerciseVideo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseVideo extends EntityPathBase<ExerciseVideo> {

    private static final long serialVersionUID = -1296744321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseVideo exerciseVideo = new QExerciseVideo("exerciseVideo");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QExercise exercise;

    public final StringPath exerciseVideoUrl = createString("exerciseVideoUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath videoTitle = createString("videoTitle");

    public QExerciseVideo(String variable) {
        this(ExerciseVideo.class, forVariable(variable), INITS);
    }

    public QExerciseVideo(Path<? extends ExerciseVideo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseVideo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseVideo(PathMetadata metadata, PathInits inits) {
        this(ExerciseVideo.class, metadata, inits);
    }

    public QExerciseVideo(Class<? extends ExerciseVideo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exercise = inits.isInitialized("exercise") ? new QExercise(forProperty("exercise"), inits.get("exercise")) : null;
    }

}

