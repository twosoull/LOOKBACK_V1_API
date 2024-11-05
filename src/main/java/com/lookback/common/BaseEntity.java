package com.lookback.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false) //생성일은 수정할 일이 없기 때문에 막아준다.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreateAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now; //업데이트에도 넣는 이유는 updateDate가 비어져 있으면 후에 쿼리를 날릴 때에 null값으로 인해 지저분해진다.
    }

    @PreUpdate
    public void preUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

}