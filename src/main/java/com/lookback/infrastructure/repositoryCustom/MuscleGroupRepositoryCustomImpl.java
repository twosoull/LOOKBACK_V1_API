package com.lookback.infrastructure.repositoryCustom;

import com.lookback.domain.muscle.entity.QMuscleCategory;
import com.lookback.domain.muscle.entity.QMuscleGroup;
import com.lookback.presentation.muscle.dto.MuscleGroupDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MuscleGroupRepositoryCustomImpl implements MuscleGroupRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MuscleGroupRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<MuscleGroupDto>  findMuscleCategoriesByExercise(List<Long> exerciseIds) {
        QMuscleGroup muscleGroup = QMuscleGroup.muscleGroup;
        QMuscleCategory muscleCategory = QMuscleCategory.muscleCategory;
        QMuscleCategory muscleCategory2 = new QMuscleCategory("mc2");

        List<Tuple> result = queryFactory.select(muscleGroup.exercise.id,
                        muscleCategory2.muscleName)
                .from(muscleGroup)
                .join(muscleCategory).on(muscleGroup.muscleCategory.id.eq(muscleCategory.id))
                .leftJoin(muscleCategory2).on(muscleCategory2.parent.id.eq(muscleCategory.id))
                .where(muscleGroup.exercise.id.in(exerciseIds))
                .fetch();

        List<MuscleGroupDto> list = result.stream().map(r -> MuscleGroupDto.fromMuscleGroup(
                r.get(muscleCategory2.muscleName)
        )).toList();

        return list;
/*
        SELECT mg.EXERCISE_ID,mc.MUSCLE_CATEGORY_ID, mc.MUSCLE_NAME , mc2.MUSCLE_CATEGORY_ID , mc2.MUSCLE_NAME
        FROM MUSCLE_GROUP mg
        JOIN MUSCLE_CATEGORY mc
        ON mg.MUSCLE_CATEGORY_ID = mc.MUSCLE_CATEGORY_ID
        LEFT JOIN MUSCLE_CATEGORY mc2
        ON mc.PARENT_ID  = mc2.MUSCLE_CATEGORY_ID
        WHERE mg.EXERCISE_ID in (1,2);*/

    }
}
