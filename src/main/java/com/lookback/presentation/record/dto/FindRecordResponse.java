package com.lookback.presentation.record.dto;

import com.lookback.domain.muscle.entity.MuscleCategory;
import com.lookback.domain.muscle.entity.MuscleGroup;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.Record;
import com.lookback.presentation.muscle.dto.MuscleCategoryDto;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FindRecordResponse {

    private Long recordId;
    private Long trainingId;
    private String recordDate;
    private boolean isPt;
    private List<MuscleGroupDto> muscleGroup;

/*
    public static List<FindRecordResponse> getUserRecordsDtosFromEntity(List<Record> findRecords ) {
        List<FindRecordResponse> findRecordResponses = new ArrayList<>();

        if(findRecords != null) {
            for(Record record : findRecords) {
                FindRecordResponse findRecordResponse = new FindRecordResponse();
                findRecordResponse.setRecordId(record.getId());
                if(record.getTraining() != null) {
                    findRecordResponse.setTrainingId(record.getTraining().getId());
                }
                findRecordResponse.setMuscleGroup(getMuscleGroupDtos(record));
                findRecordResponses.add(findRecordResponse);
            }
        }
        return findRecordResponses;
    }

    private static List<MuscleGroupDto> getMuscleGroupDtos(Record record) {
        List<MuscleGroupDto> muscleGroupDtos = new ArrayList<>();

        if(record != null) {
            List<ExerciseRecord> exerciseRecords = record.getExerciseRecords();
            for(ExerciseRecord exerciseRecord : exerciseRecords) {
                List<MuscleGroup> muscleGroups = exerciseRecord.getExercise().getMuscleGroups();
                for(MuscleGroup muscleGroup : muscleGroups) {
                    MuscleCategory muscleCategory = muscleGroup.getMuscleCategory();
                    MuscleCategoryDto muscleCategoryDto = getMuscleCategoryDto(muscleCategory);

                    MuscleGroupDto muscleGroupDto = new MuscleGroupDto();
                    muscleGroupDto.setMuscleCategory(muscleCategoryDto);
                    muscleGroupDtos.add(muscleGroupDto);
                }
            }
        }

        return muscleGroupDtos;
    }

    private static MuscleCategoryDto getMuscleCategoryDto(MuscleCategory muscleCategory) {
        MuscleCategoryDto muscleCategoryDto = new MuscleCategoryDto();
        if(muscleCategory != null) {
            muscleCategoryDto.setMuscleName(muscleCategory.getMuscleCategoryName());
            muscleCategoryDto.setMuscleCategoryName(muscleCategory.getMuscleCategoryName());
        }
        return muscleCategoryDto;
    }*/
}
