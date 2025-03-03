package com.lookback.infrastructure.repositoryImpl;

import com.lookback.domain.common.handler.exception.RestApiException;
import com.lookback.domain.user.entity.Users;
import com.lookback.domain.user.repository.UserRepository;
import com.lookback.infrastructure.repositoryORM.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.lookback.domain.common.handler.exception.errorCode.CommonErrorCode.RETRIEVE_ERROR;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public List<Users> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public List<Users> findByTrainerYn(String trainerYn) {
        return userJpaRepository.findByTrainerYn(trainerYn);
    }

    @Override
    public List<Users> findStudentsByTrainerOrderByUserNameAsc(Long trainerId) {

        return userJpaRepository.findStudentsByTrainerOrderByUserNameAsc(trainerId);
    }

    @Override
    public List<Users> findStudentsByTrainerOrderByRecentDesc(Long trainerId) {
        return userJpaRepository.findStudentsByTrainerOrderByRecentDesc(trainerId);
    }

    @Override
    public List<Users> findStudentsByTrainerAndUserNameOrderByUserNameAsc(Long trainerId , String userName) {
        return userJpaRepository.findStudentsByTrainerAndUserNameOrderByUserNameAsc(trainerId, userName);
    }

    @Override
    public Users findById(Long usersId) {
        return userJpaRepository.findById(usersId).orElseThrow(
                () -> new RestApiException(RETRIEVE_ERROR)
        );
    }

    @Override
    public Users findByEmail(String email) {
        return userJpaRepository.findByEmail(email).orElseThrow(
                () -> new RestApiException(RETRIEVE_ERROR)
        );
    }

    @Override
    public Users save(Users users) {
        return userJpaRepository.save(users);
    }

    @Override
    public Optional<Users> findByKakaoId(String kakaoId) {
        return userJpaRepository.findByKakaoId(kakaoId);
    }

}
