package com.lookback.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCancelMember is a Querydsl query type for CancelMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCancelMember extends EntityPathBase<CancelMember> {

    private static final long serialVersionUID = 1882508587L;

    public static final QCancelMember cancelMember = new QCancelMember("cancelMember");

    public final com.lookback.common.QBaseEntity _super = new com.lookback.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath reason = createString("reason");

    public final StringPath reasonDetail = createString("reasonDetail");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCancelMember(String variable) {
        super(CancelMember.class, forVariable(variable));
    }

    public QCancelMember(Path<? extends CancelMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCancelMember(PathMetadata metadata) {
        super(CancelMember.class, metadata);
    }

}

