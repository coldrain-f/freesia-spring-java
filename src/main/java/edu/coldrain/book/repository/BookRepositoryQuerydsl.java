package edu.coldrain.book.repository;

import edu.coldrain.book.entity.BookEntity;

import java.util.List;

public interface BookRepositoryQuerydsl {

    List<BookEntity> findAllByQuerydsl();
}
