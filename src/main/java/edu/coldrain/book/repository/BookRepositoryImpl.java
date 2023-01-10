package edu.coldrain.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.awt.print.Book;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public BookRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Book> findAllByQuerydsl() {
        return null;
    }
}
