package edu.coldrain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.dto.CategoryResponse;
import edu.coldrain.dto.QCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static edu.coldrain.entity.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryQuerydsl {

    private final JPAQueryFactory query;

    public CategoryRepositoryImpl(final EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Page<CategoryResponse> findAllByQuerydsl(Pageable pageable) {
        final List<CategoryResponse> content = query.select(
                        new QCategoryResponse(
                                category.id,
                                category.name,
                                category.createdAt,
                                category.modifiedAt
                        )
                ).
                from(category)
                .where(category.isDeleted.eq(false))
                .orderBy(category.createdAt.desc())
                .offset(pageable.getOffset())
                .limit((pageable.getPageSize()))
                .fetch();

        final Long total = query
                .select(category.count())
                .from(category)
                .where(category.isDeleted.eq(false))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<CategoryResponse> findAllByBookId(Long bookId, Pageable pageable) {
        // Todo: 조인문이 없어도 카테고리와 단어장의 조인 쿼리문이 날아가는지 확인 필요.
        final List<CategoryResponse> content = query.select(
                        new QCategoryResponse(
                                category.id,
                                category.name,
                                category.createdAt,
                                category.modifiedAt
                        ))
                .from(category)
                .where(
                        category.book.id.eq(bookId)
                                .and(category.isDeleted.eq(false))
                )
                .orderBy(category.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long total = query.select(category.count())
                .from(category)
                .where(
                        category.book.id.eq(bookId)
                                .and(category.isDeleted.eq(false))
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
