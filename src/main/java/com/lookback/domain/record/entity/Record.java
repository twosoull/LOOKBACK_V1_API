package com.lookback.domain.record.entity;

import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RECORD")
public class Record {

    @Id @GeneratedValue
    @Column(name = "RECORD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private Users users;


    private Long rating;
    private String comment;
    private LocalDateTime recordDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
