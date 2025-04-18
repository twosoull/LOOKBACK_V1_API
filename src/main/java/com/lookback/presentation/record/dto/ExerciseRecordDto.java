package com.lookback.presentation.record.dto;

import com.lookback.domain.common.constant.enums.ExerciseTypeEnum;
import com.lookback.domain.record.dto.ExerciseRecordDetailDomainDto;
import com.lookback.domain.record.dto.ExerciseRecordDomainDto;
import com.lookback.domain.record.dto.UploadFileDomainDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExerciseRecordDto {

    private Long exerciseRecordId;
    private Long exerciseId;
    private String exerciseName;
    private String exerciseType;
    private Integer sets;
    private Integer weight;
    private Integer ord;
    private String memo;
    private String agonistMuscleName;
    private String synergistMuscleName;
    private List<UploadFileDto> uploadFileDtos = new ArrayList<>();
    private List<ExerciseRecordDetailDto> exerciseRecordDetails;

    public ExerciseRecordDto() {}

    public static ExerciseRecordDto of(Long exerciseRecordId, String exerciseName, Integer sets, Integer weight, Integer ord) {
        return ExerciseRecordDto.builder()
                .exerciseRecordId(exerciseRecordId)
                .exerciseName(exerciseName)
                .sets(sets)
                .weight(weight)
                .ord(ord)
                .build();
    }

    public static ExerciseRecordDto of(Long exerciseRecordId, Long exerciseId, String exerciseName, String exerciseType, String memo, Integer ord, String agonistMuscleName, String synergistMuscleName, List<ExerciseRecordDetailDomainDto> exerciseRecordDetailDomainDtos, List<UploadFileDomainDto> uploadFileDomainDtos) {
        return ExerciseRecordDto.builder()
                .exerciseRecordId(exerciseRecordId)
                .exerciseId(exerciseId)
                .exerciseName(exerciseName)
                .exerciseType(exerciseType)
                .memo(memo)
                .ord(ord)
                .agonistMuscleName(agonistMuscleName)
                .synergistMuscleName(synergistMuscleName)
                .uploadFileDtos(UploadFileDto.listOf(uploadFileDomainDtos))
                .exerciseRecordDetails(ExerciseRecordDetailDto.listOf(exerciseRecordDetailDomainDtos))
                .build();
    }

    public static List<ExerciseRecordDto> listOf(List<ExerciseRecordDomainDto> exerciseRecordDomainDtos) {
        return exerciseRecordDomainDtos !=null ?
                exerciseRecordDomainDtos.stream().map(er -> ExerciseRecordDto.of(
                er.getExerciseRecordId(),
                er.getExerciseId(),
                er.getExerciseName(),
                er.getExerciseType().name(),
                er.getMemo(),
                er.getOrd(),
                er.getAgonistMuscleName(),
                er.getSynergistMuscleName(),
                er.getExerciseRecordDetails(),
                er.getUploadFileDomainDto()
        )).toList() :null;
    }
}
