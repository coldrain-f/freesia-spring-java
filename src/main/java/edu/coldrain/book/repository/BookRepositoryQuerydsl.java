package edu.coldrain.book.repository;

import edu.coldrain.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryQuerydsl {

    Page<Book> findAllByQuerydsl(Pageable pageable);
}
