package edu.coldrain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class CategoryRepositoryImpl implements CategoryRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public CategoryRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
