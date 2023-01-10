package edu.coldrain.repository;

import edu.coldrain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryQuerydsl {

    Page<Book> findAllByQuerydsl(Pageable pageable);
}
