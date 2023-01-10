package edu.coldrain.book.repository;

import edu.coldrain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long>, BookRepositoryQuerydsl  {
}
