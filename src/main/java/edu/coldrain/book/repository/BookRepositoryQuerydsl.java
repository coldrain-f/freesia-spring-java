package edu.coldrain.book.repository;

import java.awt.print.Book;
import java.util.List;

public interface BookRepositoryQuerydsl {

    List<Book> findAllByQuerydsl();
}
