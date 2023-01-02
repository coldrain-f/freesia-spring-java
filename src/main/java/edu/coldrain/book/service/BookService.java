package edu.coldrain.book.service;

import edu.coldrain.book.dto.BookCreateRequest;
import edu.coldrain.book.entity.BookEntity;
import edu.coldrain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Long create(final BookCreateRequest bookCreateRequest) {
        final BookEntity bookEntity = bookCreateRequest.toEntity();
        return bookRepository.save(bookEntity).getId();
    }
}
