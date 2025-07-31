package com.lookback.domain.exercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExercise is a Querydsl query type for Exercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExercise extends EntityPathBase<Exercise> {

    private static final long serialVersionUID = 1419238748L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExercise exercise = new QExercise("exercise");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    public final NumberPath<Integer> caloriesBurned = createNumber("caloriesBurned", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath description = createString("description");

    public final com.lookback.domain.muscle.entity.QEquipment equipment;

    public final StringPath exerciseLevel = createString("exerciseLevel");

    public final StringPath exerciseName = createString("exerciseName");

    public final EnumPath<com.lookback.domain.common.constant.enums.ExerciseTypeEnum> exerciseType = createEnum("exerciseType", com.lookback.domain.common.constant.enums.ExerciseTypeEnum.class);

    public final ListPath<ExerciseVideo, QExerciseVideo> exerciseVideos = this.<ExerciseVideo, QExerciseVideo>createList("exerciseVideos", ExerciseVideo.class, QExerciseVideo.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final ListPath<com.lookback.domain.muscle.entity.MuscleGroup, com.lookback.domain.muscle.entity.QMuscleGroup> muscleGroups = this.<com.lookback.domain.muscle.entity.MuscleGroup, com.lookback.domain.muscle.entity.QMuscleGroup>createList("muscleGroups", com.lookback.domain.muscle.entity.MuscleGroup.class, com.lookback.domain.muscle.entity.QMuscleGroup.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QExercise(String variable) {
        this(Exercise.class, forVariable(variable), INITS);
    }

    public QExercise(Path<? extends Exercise> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExercise(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExercise(PathMetadata metadata, PathInits inits) {
        this(Exercise.class, metadata, inits);
    }

    public QExercise(Class<? extends Exercise> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.equipment = inits.isInitialized("equipment") ? new com.lookback.domain.muscle.entity.QEquipment(forProperty("equipment")) : null;
    }

}

