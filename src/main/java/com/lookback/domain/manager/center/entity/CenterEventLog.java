package com.lookback.domain.manager.center.entity;

import com.lookback.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CENTER_EVENT_LOG")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CenterEventLog extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CENTER_EVENT_LOG_ID")
    private Long id;

    @Column(nullable = false)
    private Long centerId;

    private Long actorUserId;            // 누가 했는가(관리자/트레이너/회원)

    @Column(length = 32)
    private String actorRole;            // OWNER/SUPER_ADMIN/TRAINER/MEMBER/SYSTEM 등

    @Column(length = 32, nullable = false)
    private String entityType;           // TRAINER / PRODUCT / ORDER ...

    @Column(nullable = false)
    private Long entityId;               // 대상 PK

    @Column(length = 64, nullable = false)
    private String action;               // TRAINER_STATUS_CHANGED / PRODUCT_CREATED ...

    @Column(length = 64)
    private String statusFrom;           // 이전 상태(문자열)

    @Column(length = 64)
    private String statusTo;             // 변경 상태(문자열)

    @Column(length = 255)
    private String reason;               // 사유(선택)

    // MySQL 8+ JSON 컬럼. (만약 JSON 미지원이면 아래 주석으로 대체)
    @Column(columnDefinition = "json")
    private String payloadJson;

    @Column(length = 64)
    private String requestId;            // X-REQUEST-ID 등

    @Column(length = 45)
    private String ipAddr;               // IPv4/IPv6
}
