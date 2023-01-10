package edu.coldrain.repository;

import edu.coldrain.dto.BookDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryQuerydsl {

    Page<BookDetailResponse> findAllByQuerydsl(Pageable pageable);
}
