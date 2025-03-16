package com.lookback.presentation.record.dto;

import com.lookback.common.converter.CommonConverter;
import com.lookback.domain.record.entity.Record;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class FindRecordResponse {

    List<FindRecordResponse> list = new ArrayList<>();
    private Long recordId;
    private Long trainingId;
    private String recordDate;
    private String dayOfWeek;
    private boolean isPt;

    List<String> usedMuscleNames = new ArrayList<>();
    List<String> recordOfExerciseTypes = new ArrayList<>();

    public static FindRecordResponse addList(List<FindRecordResponse> findRecordResponseList) {
        FindRecordResponse findRecordResponse = new FindRecordResponse();
        findRecordResponse.setList(findRecordResponseList);
        return findRecordResponse;
    }
    public static FindRecordResponse create(Record record, List<String> useMuscleNames, List<String> recordOfExerciseTypes) {
        FindRecordResponse rs = new FindRecordResponse();
        rs.setRecordId(record.getId());
        rs.setPt(record.getTraining() != null ? true : false);
        rs.setRecordDate(CommonConverter.formatData(record.getRecordDate().toString()));
        rs.setDayOfWeek(CommonConverter.formatWeekOfKorea(record.getRecordDate().toString()));
        rs.setUsedMuscleNames(useMuscleNames);
        rs.setRecordOfExerciseTypes(recordOfExerciseTypes);

        return rs;
    }

}
