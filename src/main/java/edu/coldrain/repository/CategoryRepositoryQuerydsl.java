package edu.coldrain.repository;

import edu.coldrain.dto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepositoryQuerydsl {

    Page<CategoryResponse> findAllByQuerydsl(Pageable pageable);

    Page<CategoryResponse> findAllByBookId(Long bookId, Pageable pageable);
}
