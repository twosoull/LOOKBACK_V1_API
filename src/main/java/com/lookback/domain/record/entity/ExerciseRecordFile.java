package com.lookback.domain.record.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ExerciseRecordFile {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_RECORD_ID")
    private ExerciseRecord exerciseRecord;

    private String fileName;
    private String path;
    private String orgFileName;
    private String fileType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
