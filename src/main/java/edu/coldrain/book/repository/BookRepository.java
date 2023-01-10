package edu.coldrain.book.repository;

import edu.coldrain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryQuerydsl  {
}
