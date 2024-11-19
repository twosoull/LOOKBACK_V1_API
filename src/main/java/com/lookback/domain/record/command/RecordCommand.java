package com.lookback.domain.record.command;

import com.lookback.domain.record.entity.Record;
import com.lookback.domain.user.entity.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class RecordCommand {

    public record Save(Long recordId,
                       Long userId,
                       Long rating,
                       String comment,
                       LocalDateTime recordDate
    ){}

    public record Saved(Long recordId,
                        Long rating,
                        String comment,
                        LocalDateTime recordDate,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt
    ){}

    public static RecordCommand.Saved of(Record record) {
        return new RecordCommand.Saved(record.getId(),
                                       record.getRating(),
                                       record.getComment(),
                                       record.getRecordDate(),
                                       record.getCreatedAt(),
                                       record.getUpdatedAt());
    }
}
