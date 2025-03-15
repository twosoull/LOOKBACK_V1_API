package com.lookback.domain.user.service;

import com.lookback.common.context.UserContext;
import com.lookback.domain.common.constant.enums.TrainingStatus;
import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.command.StringUtil;
import com.lookback.domain.user.entity.Trainer;
import com.lookback.domain.user.entity.Training;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.TrainerRepository;
import com.lookback.domain.user.repository.TrainingRepository;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.infrastructure.queryDto.UserTrainingQueryDto;
import com.lookback.presentation.trainer.dto.UserTrainingDto;
import com.lookback.presentation.users.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RETRIEVE_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingService {

    private final TrainerRepository trainerRepository;
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    /** 1. [트레이너] 회원관리**/
    /**
     * 회원 목록 (가나다순, 최근 수업일 순)
     * */
    @Transactional
    public UserTrainingDto findTrainingsForTrainer(HttpServletRequest request, FindTrainingUsersRequest requestDto){
        Users user = UserContext.getUser(request);
        Long trainerId = user.getId();
        String sortBy  = requestDto.getSortBy();
        if(!StringUtil.isNullOrEmpty(requestDto.getSortBy()) && requestDto.getSortBy().equals("userName")){
            sortBy = "userName";
        }

        List<UserTrainingQueryDto> findQueryDto = trainingRepository.findTrainingsForTrainerOrderBySortByType(trainerId, TrainingStatus.IN_PROGRESS, sortBy);
        List<UserTrainingDto> list = findQueryDto.stream()
                .map(queryDto -> new UserTrainingDto(
                        queryDto.getId(),
                        queryDto.getUserName(),
                        queryDto.getBirthDt(),
                        queryDto.getLatestCreatedAt()
                )).collect(Collectors.toList());


        return UserTrainingDto.fromList(list,list.size());
    }


/*    @Transactional
    public FindTrainingUsersResponse findAllTrainingUsers(FindTrainingUsersRequest request) {
        //TODO trainerID는 공통으로 가져오기

        TrainingServiceValidator.findTrainingUsersRequestValid(request);

        //String trainingStatus    = StringUtil.isNullOrEmpty(request.getTrainingStatus()) ? "ACTIVE" : request.getTrainingStatus();
        String sortBy            = StringUtil.isNullOrEmpty(request.getSortBy()) ? "name" : request.getTrainingStatus();
        //request.setTrainingStatus(trainingStatus);
        request.setSortBy(sortBy);

        List<Users> findStudents;
        try{
            if ("recent".equals(request.getSortBy())){
                findStudents = userRepository.findStudentsByTrainerOrderByRecentDesc(request.getTrainerId());
            } else {
                findStudents = userRepository.findStudentsByTrainerOrderByUserNameAsc(request.getTrainerId());
            }
        } catch (Exception e){
            log.debug(e.getMessage());
            throw new RestApiException(RETRIEVE_ERROR);
        }

        return FindTrainingUsersResponse.getFindTrainingUsersResponse(findStudents);
    }*/

    /**
     * 회원 검색 (트레이너의 수강생인 회원 중 이름으로 검색)
     * */
    @Transactional
    public List<Users> findTrainingUsersByUserName(FindTrainingUsersRequest request) {
        //TODO trainerID는 공통으로 가져오기

        TrainingServiceValidator.findTrainingUsersRequestValid(request);

        List<Users> findStudents;
        try {
            findStudents = userRepository.findStudentsByTrainerAndUserNameOrderByUserNameAsc(request.getTrainerId(), request.getUserName());
        }catch (Exception e){
            throw new RestApiException(RETRIEVE_ERROR);
        }
        return findStudents;
    }

    /**
     * 회원 추가 (카카오톡 초대, 초대링크 복사, 다른 앱으로 초대)
     * */
    @Transactional
    public int saveTrainingUser(SaveTrainingUserRequest request) {
        //TODO trainerID는 공통으로 가져오기

        TrainingServiceValidator.saveTrainingUserRequestValid(request);

        Trainer findTrainer = trainerRepository.findById(request.getTrainerId());
        Users findStudent = userRepository.findById(request.getStudentId());

        //TODO 소스정리
        Training training;
        try {
            training = Training.create(findTrainer, findStudent, TrainingStatus.IN_PROGRESS);
            trainingRepository.save(training);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new RestApiException(RETRIEVE_ERROR);
        }

        int result = 0;
        if(training != null){ result = 1; }

        return result;
    }

    /**
     * 회원 연결해제
     * */
    @Transactional
    public void cancelTraining(UpdateTrainingUsersRequest request) {

        Training training = trainingRepository.findById(request.getTrainingId());
        training.cancel();

        //TODO 리턴으로 해제 값을 보내준다. 실패시 오류 값 보내기
    }

}
