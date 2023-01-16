package edu.coldrain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.dto.BookResponse;
import edu.coldrain.dto.QBookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static edu.coldrain.entity.QBook.book;


public class BookRepositoryImpl implements BookRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public BookRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<BookResponse> findAllByQuerydsl(Pageable pageable) {
        final List<BookResponse> content = query.select(
                        new QBookResponse(
                                book.id,
                                book.name,
                                book.content,
                                book.language,
                                book.author.username,
                                book.shareStatus,
                                book.createdAt,
                                book.modifiedAt
                        )
                )
                .from(book)
                .where(book.isDeleted.eq(false))
                .orderBy(book.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long total = query
                .select(book.count())
                .from(book)
                .where(book.isDeleted.eq(false))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<BookResponse> findMyBook(Pageable pageable, Long userId) {
        final List<BookResponse> content = query.select(
                        new QBookResponse(
                                book.id,
                                book.name,
                                book.content,
                                book.language,
                                book.author.username,
                                book.shareStatus,
                                book.createdAt,
                                book.modifiedAt
                        )
                )
                .from(book)
                .where(
                        book.isDeleted.eq(false)
                                .and(book.author.id.eq(userId))
                )
                .orderBy(book.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long total = query.select(book.count())
                .from(book)
                .where(
                        book.isDeleted.eq(false)
                                .and(book.author.id.eq(userId))
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
