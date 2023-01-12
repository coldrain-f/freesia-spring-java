package edu.coldrain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.dto.CategoryResponse;
import edu.coldrain.dto.QCategoryResponse;
import edu.coldrain.entity.QCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static edu.coldrain.entity.QCategory.*;

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
}
