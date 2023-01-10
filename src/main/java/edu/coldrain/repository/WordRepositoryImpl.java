package edu.coldrain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class WordRepositoryImpl implements WordRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public WordRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
}
