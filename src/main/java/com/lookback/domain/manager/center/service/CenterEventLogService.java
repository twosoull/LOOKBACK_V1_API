package com.lookback.domain.centerlog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lookback.domain.manager.center.entity.CenterEventLog;
import com.lookback.domain.manager.center.repository.CenterEventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CenterEventLogService {

    private final CenterEventLogRepository centerEventLogRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public Long log(
            Long centerId,
            Long actorUserId,
            String actorRole,
            String entityType,
            Long entityId,
            String action,
            String statusFrom,
            String statusTo,
            String reason,
            Map<String, Object> payload,   // 예: Map.of("ui","manager","auto",false)
            String requestId,
            String ipAddr
    ) {
        String payloadJson = toJsonSafe(payload);

        CenterEventLog log = CenterEventLog.builder()
                .centerId(centerId)
                .actorUserId(actorUserId)
                .actorRole(actorRole)
                .entityType(entityType)
                .entityId(entityId)
                .action(action)
                .statusFrom(statusFrom)
                .statusTo(statusTo)
                .reason(reason)
                .payloadJson(payloadJson)
                .requestId(requestId)
                .ipAddr(ipAddr)
                .build();

        CenterEventLog saved = centerEventLogRepository.save(log);
        return saved.getId();
    }

    // 편의 오버로드: payload 없는 경우
    @Transactional
    public Long log(
            Long centerId,
            Long actorUserId,
            String actorRole,
            String entityType,
            Long entityId,
            String action,
            String statusFrom,
            String statusTo,
            String reason,
            String requestId,
            String ipAddr
    ) {
        return log(centerId, actorUserId, actorRole, entityType, entityId, action,
                statusFrom, statusTo, reason, Collections.emptyMap(), requestId, ipAddr);
    }

    private String toJsonSafe(Map<String, Object> payload) {
        if (payload == null || payload.isEmpty()) return "{}";
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            // 혹시 직렬화 실패하면 최소한의 문자열로 저장
            return "{\"_error\":\"payload_serialize_failed\"}";
        }
    }
}
