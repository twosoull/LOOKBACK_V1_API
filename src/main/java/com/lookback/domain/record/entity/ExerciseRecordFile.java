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
    @Column(name="EXERCISE_RECORD_FILE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXERCISE_RECORD_ID",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ExerciseRecord exerciseRecord;

    private String fileName;
    private String path;
    private String orgFileName;
    private String fileType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
