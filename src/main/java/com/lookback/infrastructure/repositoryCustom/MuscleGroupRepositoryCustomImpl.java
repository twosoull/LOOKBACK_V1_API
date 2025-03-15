package com.lookback.infrastructure.repositoryCustom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class MuscleGroupRepositoryCustomImpl implements MuscleGroupRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public MuscleGroupRepositoryCustomImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public void findMuscleCategoriesByExercise() {

    }
}
