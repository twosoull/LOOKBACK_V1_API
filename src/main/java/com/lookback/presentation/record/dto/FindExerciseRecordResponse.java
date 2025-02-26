package com.lookback.presentation.record.dto;

import com.lookback.domain.exercise.entity.Exercise;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.ExerciseRecordFile;
import com.lookback.domain.record.entity.Record;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FindExerciseRecordResponse {

    private String trainerName;
    private boolean isPt;
    private LocalDate recordDate;
    private LocalTime recordTimeStart;
    private LocalTime recordTimeEnd;
    List<ExerciseRecordDto> exerciseRecords = new ArrayList<>();

    public static FindExerciseRecordResponse getExerciseRecordDetailFromEntity(List<ExerciseRecord> exerciseRecords, Record record) {
        FindExerciseRecordResponse findExerciseRecordResponse = new FindExerciseRecordResponse();
        List<ExerciseRecordDto> exerciseRecordDtos = getExerciseRecordDtos(exerciseRecords);

        boolean isPt = record.getRecordShare() != null;
        if(isPt){
            findExerciseRecordResponse.setTrainerName(record.getRecordShare().getTraining().getTrainer().getUser().getNickName());
            findExerciseRecordResponse.setRecordDate(record.getRecordDate());
            findExerciseRecordResponse.setRecordTimeStart(record.getRecordTimeStart());
            findExerciseRecordResponse.setRecordTimeEnd(record.getRecordTimeEnd());
        }

        findExerciseRecordResponse.setExerciseRecords(exerciseRecordDtos);
        findExerciseRecordResponse.setPt(isPt);

        return findExerciseRecordResponse;
    }

    public static List<ExerciseRecordDto> getExerciseRecordDtos(List<ExerciseRecord> exerciseRecords) {
        List<ExerciseRecordDto> exerciseRecordDtos = new ArrayList<>();
        for(ExerciseRecord exerciseRecord : exerciseRecords) {
            ExerciseRecordDto exerciseRecordDto = new ExerciseRecordDto();
            Exercise exercise = exerciseRecord.getExercise();
            if(exercise != null){
                exerciseRecordDto.setExerciseName(exerciseRecord.getExercise().getExerciseName());
            }
            exerciseRecordDto.setSets(exerciseRecordDto.getSets());
            exerciseRecordDto.setWeight(exerciseRecord.getWeight());
            exerciseRecordDto.setOrd(exerciseRecord.getOrd());


            List<ExerciseRecordMediaDto> exerciseRecordMediaDtos = new ArrayList<>();
            for(ExerciseRecordFile file :  exerciseRecord.getExerciseRecordFiles()){
                ExerciseRecordMediaDto exerciseRecordMediaDto = new ExerciseRecordMediaDto();
                exerciseRecordMediaDto.setFileName(file.getFileName());
                exerciseRecordMediaDto.setFileType(file.getFileType());
                exerciseRecordMediaDto.setPath(file.getPath());
                exerciseRecordMediaDto.setOrgFileName(file.getOrgFileName());
                exerciseRecordMediaDto.setExerciseRecordMediaId(file.getId());
                exerciseRecordMediaDtos.add(exerciseRecordMediaDto);
            }

            exerciseRecordDto.setExerciseRecordMediaDtos(exerciseRecordMediaDtos);
            exerciseRecordDtos.add(exerciseRecordDto);
        }
        return exerciseRecordDtos;
    }


}
