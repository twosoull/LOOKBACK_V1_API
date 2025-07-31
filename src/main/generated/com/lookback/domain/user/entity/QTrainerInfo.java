package com.lookback.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTrainerInfo is a Querydsl query type for TrainerInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrainerInfo extends EntityPathBase<TrainerInfo> {

    private static final long serialVersionUID = -1475158868L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrainerInfo trainerInfo1 = new QTrainerInfo("trainerInfo1");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> endAt = createDateTime("endAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath infoContent = createString("infoContent");

    public final StringPath infoName = createString("infoName");

    public final StringPath infoType = createString("infoType");

    public final StringPath inProgress = createString("inProgress");

    public final StringPath privateYn = createString("privateYn");

    public final DateTimePath<java.time.LocalDateTime> startAt = createDateTime("startAt", java.time.LocalDateTime.class);

    public final QTrainer trainerInfo;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTrainerInfo(String variable) {
        this(TrainerInfo.class, forVariable(variable), INITS);
    }

    public QTrainerInfo(Path<? extends TrainerInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTrainerInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTrainerInfo(PathMetadata metadata, PathInits inits) {
        this(TrainerInfo.class, metadata, inits);
    }

    public QTrainerInfo(Class<? extends TrainerInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.trainerInfo = inits.isInitialized("trainerInfo") ? new QTrainer(forProperty("trainerInfo"), inits.get("trainerInfo")) : null;
    }

}

