package edu.coldrain.book.repository;

import edu.coldrain.book.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryQuerydsl {

    Page<BookEntity> findAllByQuerydsl(Pageable pageable);
}
