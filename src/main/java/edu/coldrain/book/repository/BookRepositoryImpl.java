package edu.coldrain.book.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.book.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static edu.coldrain.book.entity.QBookEntity.bookEntity;

public class BookRepositoryImpl implements BookRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public BookRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<BookEntity> findAllByQuerydsl(Pageable pageable) {
        final List<BookEntity> content = query.select(bookEntity)
                .from(bookEntity)
                .where(bookEntity.isDeleted.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long total = query
                .select(bookEntity.count())
                .from(bookEntity)
                .where(bookEntity.isDeleted.eq(false))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
