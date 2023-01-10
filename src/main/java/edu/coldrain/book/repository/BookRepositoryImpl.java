package edu.coldrain.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.book.entity.BookEntity;

import javax.persistence.EntityManager;
import java.awt.print.Book;
import java.util.List;

import static edu.coldrain.book.entity.QBookEntity.bookEntity;

public class BookRepositoryImpl implements BookRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public BookRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<BookEntity> findAllByQuerydsl() {
        return query.selectFrom(bookEntity)
                .where(bookEntity.isDeleted.eq(false))
                .fetch();
    }
}
