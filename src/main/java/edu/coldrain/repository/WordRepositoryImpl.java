package edu.coldrain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.dto.QWordResponse;
import edu.coldrain.dto.WordResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static edu.coldrain.entity.QWord.word;

public class WordRepositoryImpl implements WordRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public WordRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<WordResponse> findAllByQuerydsl(Pageable pageable) {
        final List<WordResponse> content = query.select(
                        new QWordResponse(
                                word.id,
                                word.name,
                                word.createdAt,
                                word.modifiedAt
                        )
                )
                .from(word)
                .where(word.isDeleted.eq(false))
                .orderBy(word.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long total = query.select(word.count())
                .from(word)
                .where(word.isDeleted.eq(false))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<WordResponse> findAllByCategoryId(Long categoryId, Pageable pageable) {
        final List<WordResponse> content = query.select(
                        new QWordResponse(
                                word.id,
                                word.name,
                                word.createdAt,
                                word.modifiedAt
                        )
                )
                .from(word)
                .where(
                        word.category.id.eq(categoryId).and(
                                word.isDeleted.eq(false)
                        )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long total = query.select(word.count())
                .from(word)
                .where(
                        word.category.id.eq(categoryId).and(
                                word.isDeleted.eq(false)
                        )
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
