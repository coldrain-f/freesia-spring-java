package edu.coldrain.repository;

import edu.coldrain.dto.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryQuerydsl {

    Page<BookResponse> findAllByQuerydsl(Pageable pageable);

    Page<BookResponse> findMyBook(Pageable pageable, Long userId);
}
