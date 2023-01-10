package edu.coldrain.repository;

import edu.coldrain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryQuerydsl  {
}
