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

    public static final QTrainerInfo trainerInfo = new QTrainerInfo("trainerInfo");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTrainer trainer;

    public final DateTimePath<java.time.LocalDateTime> trainerInfoAcquisitionDate = createDateTime("trainerInfoAcquisitionDate", java.time.LocalDateTime.class);

    public final StringPath trainerInfoContent = createString("trainerInfoContent");

    public final DateTimePath<java.time.LocalDateTime> trainerInfoEndAt = createDateTime("trainerInfoEndAt", java.time.LocalDateTime.class);

    public final StringPath trainerInfoInProgress = createString("trainerInfoInProgress");

    public final StringPath trainerInfoName = createString("trainerInfoName");

    public final DateTimePath<java.time.LocalDateTime> trainerInfoStartAt = createDateTime("trainerInfoStartAt", java.time.LocalDateTime.class);

    public final EnumPath<com.lookback.domain.common.constant.enums.TrainerInfoType> trainerInfoType = createEnum("trainerInfoType", com.lookback.domain.common.constant.enums.TrainerInfoType.class);

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
        this.trainer = inits.isInitialized("trainer") ? new QTrainer(forProperty("trainer"), inits.get("trainer")) : null;
    }

}

