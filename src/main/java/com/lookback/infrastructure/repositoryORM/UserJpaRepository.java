package com.lookback.infrastructure.repositoryORM;

import com.lookback.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
    List<Users> findByTrainerYn(@Param("trainerYn") String trainerYn);

    //TODO 나중에 정렬 쿼리를 하나로 합칠 것
    @Query("select t.student from Training t where t.trainer.id = :trainerId ORDER BY t.student.userName ASC ")
    List<Users> findStudentsByTrainerOrderByUserNameAsc(Long trainerId);


    @Query("select t.student from Training t inner join Record r on t.id = r.training.id where t.trainer.id = :trainerId order by r.createdAt DESC")
    List<Users> findStudentsByTrainerOrderByRecentDesc(Long trainerId);

    @Query("select t.student from Training t where t.trainer.id = :trainerId And t.student.userName like %:userName% ORDER BY t.student.userName ASC ")
    List<Users> findStudentsByTrainerAndUserNameOrderByUserNameAsc(Long trainerId, String userName);
}
