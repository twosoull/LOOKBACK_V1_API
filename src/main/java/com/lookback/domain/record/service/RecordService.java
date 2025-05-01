package com.lookback.domain.record.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.constant.enums.ExerciseDetailTypeEnum;
import com.lookback.domain.common.constant.enums.FileStatus;
import com.lookback.domain.common.constant.enums.ShareStatus;
import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.file.entity.UploadFile;
import com.lookback.domain.file.repository.FileRepository;
import com.lookback.domain.muscle.repository.MuscleGroupRepository;
import com.lookback.domain.record.dto.RecordWithDetailsDto;
import com.lookback.domain.record.dto.UsersDomainDto;
import com.lookback.domain.record.entity.ExerciseRecord;
import com.lookback.domain.record.entity.ExerciseRecordDetail;
import com.lookback.domain.record.entity.Record;
import com.lookback.domain.record.repository.ExerciseRecordDetailRepository;
import com.lookback.domain.record.repository.ExerciseRecordRepository;
import com.lookback.domain.record.repository.RecordRepository;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import com.lookback.presentation.record.dto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RESOURCE_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecordService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final TrainingRepository trainingRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ExerciseRecordDetailRepository exerciseRecordDetailRepository;
    private final FileRepository fileRepository;

    /**`
     * [회원]
     * 운동 기록 목록(pt와 개인 운동 목록)( 전체, pt, 개인) 카테고리로 나눠진다.
     * */
    @Transactional
    public FindRecordResponse findMembersRecordList(HttpServletRequest request, FindRecordRequest findRecordRequest) {
        if(findRecordRequest == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        //트레이너가 회원의 정보를 찾는 경우 parameter의 userId로 찾는다.
        Long usersId = UserContext.getUser(request).getUserId();
        String userType = findRecordRequest.getUserType();

        Long trainerId = null;
        if(userType != null) {
            if(userType.equals("TRAINER")) {
                usersId = findRecordRequest.getUserId();
                //본인이 트레이너라면, 세션 아이디는 트레이너가 된다.
                trainerId = UserContext.getUser(request).getUserId();
            }
        }
        //해당 유저의 정보 가져오기
        Users findMember = userRepository.findById(usersId);
        Training training = trainingRepository.findByTrainerIdAndStudentIdAndTrainingStatus(trainerId,usersId, TrainingStatus.IN_PROGRESS);
        String category = findRecordRequest.getType();
        List<Record> records = recordRepository.findByUsersId(usersId, category);

        //record 기록과 각각의 운동타입(유산소,근력,스트레칭), 근육의 부위 2뎁스( 어깨, 등, 가슴) 등을 찾아온다.
        List<FindRecordResponse> findRecordResponses = new ArrayList<>();
        for(Record r : records) {
            List<String> recordOfExerciseTypes = new ArrayList<>();
            List<String> usedMuscleNames = new ArrayList<>();
            List<ExerciseRecord> findExRecordOrdAsc = exerciseRecordRepository.findByIdOrderByOrdAsc(r.getId());

            List<Long> exerciseIds = new ArrayList<>();
            for(ExerciseRecord er : findExRecordOrdAsc) {
                recordOfExerciseTypes.add(er.getExercise().getExerciseType().name());
                exerciseIds.add(er.getExercise().getId());

                List<MuscleGroupDto> findMuscleGroup = muscleGroupRepository.findMuscleCategoriesByExercise(exerciseIds);
                findMuscleGroup.forEach(mg -> {
                    if(mg != null && mg.getCategoryParentsName() != null && !mg.getCategoryParentsName().equals("")) {
                        usedMuscleNames.add(mg.getCategoryParentsName());
                    }
                });
            }
            List<String> notDupMuscleNames = new ArrayList<>();
            for(String muscleName : usedMuscleNames) {
                if(!notDupMuscleNames.contains(muscleName)) {
                    notDupMuscleNames.add(muscleName);
                }
            }

            FindRecordResponse findRecordResponse = FindRecordResponse.create(r, notDupMuscleNames, recordOfExerciseTypes);
            findRecordResponses.add(findRecordResponse);
        }

        return FindRecordResponse.add(findRecordResponses, findMember
                ,training == null ? null : training.getId() );
    }

    /**
     * 운동 기록 상세
     * @param findRecordRequest
     * @return
     */
    public RecordWithDetailsDto findRecordDetail(FindRecordRequest findRecordRequest) {

        try{
            RecordWithDetailsDto recordWithDetailsDto = recordRepository.findRecordWithOrderedDetails(findRecordRequest.getRecordId());

            Long trainingId = recordWithDetailsDto.getTrainingId();
            if(trainingId != null) {
                Users findUsers = userRepository.findById(trainingId);
                recordWithDetailsDto.setTrainer(UsersDomainDto.createDto(findUsers.getId(), findUsers.getUserName(), findUsers.getNickName()));
            }

            return recordWithDetailsDto;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

    }

    /**
     * [회원] 기록 상세
     * - 기록 상세의 운동 목록
     * */
    @Transactional
    public FindExerciseRecordResponse findExerciseRecordById(FindExerciseRecordRequest findExerciseRecordRequest) {

        List<ExerciseRecord> exerciseRecords = exerciseRecordRepository.findByIdOrderByOrdAsc(findExerciseRecordRequest.getRecordId());
        Record record = recordRepository.findById(findExerciseRecordRequest.getRecordId());

        return FindExerciseRecordResponse.getExerciseRecordDetailFromEntity(exerciseRecords, record);
    }

    /**
     * [회원] 운동 기록 삭제
     * */
    @Transactional
    public void removeRecordById(RemoveRecordRequest removeRecordRequest) {
        RecordServiceValidator.removeRecordRequestValid(removeRecordRequest);

        Record findRecord = recordRepository.findById(removeRecordRequest.getRecordId());
        try {
            if(findRecord != null) {
                recordRepository.deleteById(findRecord.getId()); //exerciseRecord, exerciseRecordFile, ExerciseRecordDetail 자동삭제
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

    }

    /**
     * 운동 기록 저장 (세부내용 업이 날짜, 시간만 저장한다)
     * @param request
     * @param save
     * @return
     */
    @Transactional
    public SaveRecordResponse save(HttpServletRequest request, SaveRecordRequest save) {

        //TODO 일단 로그인user와 parameter의 usersId로 구분을 하자. 나중에 트레이너 본인도 자신의 기록을 작성할 수도 있으니까.
        Long usersId = UserContext.getUser(request).getUserId();

        Record saveRecord = null;
        //같지 않을 경우 현재는 pt수업이다. 그러므로 training을 record에 저장해야한다.
        if (usersId != save.getUsersId()) {
            //다른 사람이 작성하는 경우
            Training training = trainingRepository.findByTrainerIdAndStudentIdAndTrainingStatus(usersId,save.getUsersId(), TrainingStatus.IN_PROGRESS);
            if(training == null) {
                throw new RestApiException(RESOURCE_NOT_FOUND);
            }
            Users student = training.getStudent();
            saveRecord = recordRepository.save(Record.createFromSaveDto(student, training, save));

        } else {
            //본인이 본인의 기록을 작성하는 경우
            Users findUsers = userRepository.findById(usersId);
            saveRecord = recordRepository.save(Record.createFromSaveDto(findUsers, save));
        }

        return SaveRecordResponse.fromEntity(saveRecord);
    }

    /**
     * 운동 기록 디테일 저장
     * @param saveExerciseRecordRequest
     */
    @Transactional
    public void saveExerciseRecords(SaveExerciseRecordRequest saveExerciseRecordRequest) {
        Record findRecord = recordRepository.findById(saveExerciseRecordRequest.getRecordId());
        if(findRecord == null) {
            throw new RestApiException(RESOURCE_NOT_FOUND);
        }

        //TODO 벌크쿼리로 변경 필요
        //1. 운동 기록을 저장 (N건)
        saveExerciseRecordRequest.getExerciseRecords().forEach(er -> {


            ExerciseRecord findExerciseRecord = er.getExerciseRecordId() != null
                    ? exerciseRecordRepository.findById(er.getExerciseRecordId()) : null;

            ExerciseRecord targetRecord = null;
            //수정
            if(findExerciseRecord != null) {
                findExerciseRecord.setMemo(er.getMemo());
                findExerciseRecord.setOrd(er.getOrd());
                targetRecord = findExerciseRecord;
            }
            //저장
            if(targetRecord == null) {
                ExerciseRecord exerciseRecord = new ExerciseRecord();
                exerciseRecord.setExerciseById(er.getExerciseId());
                exerciseRecord.setRecord(findRecord);
                exerciseRecord.setMemo(er.getMemo());
                exerciseRecord.setOrd(er.getOrd());
                targetRecord = exerciseRecordRepository.save(exerciseRecord);
            }

            //파일 부모키 연결
            //TODO SAVED가 아닌애들만 찾을 것
            if(er.getUploadFiles() != null && er.getUploadFiles().size() > 0) {
                for(UploadFileDto uf : er.getUploadFiles()) {
                    UploadFile findFile = fileRepository.findById(uf.getUuid());
                    findFile.setReferenceId(targetRecord.getId());
                    findFile.setOrd(uf.getOrd());
                    findFile.setStatus(FileStatus.SAVED);
                };
            }

            if(er.getDelFiles() != null && er.getDelFiles().size() > 0) {
                for(UploadFileDto uf : er.getDelFiles()) {
                    UploadFile findFile = fileRepository.findById(uf.getUuid());
                    findFile.setStatus(FileStatus.DELETE);
                };
            }

            //2. 운동 기록 상세 저장
            if( er.getExerciseRecordDetails() != null && er.getExerciseRecordDetails().size() > 0) {
                for(ExerciseRecordDetailDto erd : er.getExerciseRecordDetails()) {
                    ExerciseRecordDetail findErd = erd.getExerciseRecordDetailId() != null ?
                            exerciseRecordDetailRepository.findById(erd.getExerciseRecordDetailId()) : null;
                    //수정
                    if(findErd != null) {
                        findErd.setOrd(erd.getOrd());
                        findErd.setRepsPerSet(erd.getRepsPerSet());
                        findErd.setType(ExerciseDetailTypeEnum.from(erd.getType()));
                        findErd.setWeight(erd.getWeight());
                        continue;
                    }

                    //저장
                    ExerciseRecordDetail exerciseRecordDetail = new ExerciseRecordDetail();
                    exerciseRecordDetail.setExerciseRecord(targetRecord);
                    exerciseRecordDetail.setOrd(erd.getOrd());
                    exerciseRecordDetail.setRepsPerSet(erd.getRepsPerSet());
                    exerciseRecordDetail.setWeight(erd.getWeight());
                    exerciseRecordDetail.setType(ExerciseDetailTypeEnum.from(erd.getType()));

                    exerciseRecordDetailRepository.save(exerciseRecordDetail);
                };
            }

        });

        //TODO mvp 때는 저장 성공 후 record 바로 공유상태로
        findRecord.setShareStatus(ShareStatus.SHARED);
        entityManager.flush();
        entityManager.clear();
    }
}
