package com.lookback.domain.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUploadFile extends EntityPathBase<UploadFile> {

    private static final long serialVersionUID = 195742661L;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath extension = createString("extension");

    public final StringPath fileName = createString("fileName");

    public final StringPath fullPath = createString("fullPath");

    public final NumberPath<Long> ord = createNumber("ord", Long.class);

    public final StringPath originalName = createString("originalName");

    public final NumberPath<Long> referenceId = createNumber("referenceId", Long.class);

    public final StringPath relativePath = createString("relativePath");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final EnumPath<com.lookback.domain.common.constant.enums.FileStatus> status = createEnum("status", com.lookback.domain.common.constant.enums.FileStatus.class);

    public final EnumPath<com.lookback.domain.common.constant.enums.FileType> type = createEnum("type", com.lookback.domain.common.constant.enums.FileType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ComparablePath<java.util.UUID> uuid = createComparable("uuid", java.util.UUID.class);

    public QUploadFile(String variable) {
        super(UploadFile.class, forVariable(variable));
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadFile(PathMetadata metadata) {
        super(UploadFile.class, metadata);
    }

}

