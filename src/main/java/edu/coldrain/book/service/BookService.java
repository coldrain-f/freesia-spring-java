package edu.coldrain.book.service;

import edu.coldrain.book.dto.BookCreateRequest;
import edu.coldrain.book.dto.BookUpdateRequest;
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

    public void update(final BookUpdateRequest bookUpdateRequest, final Long bookId) {
        final BookEntity bookEntity = this.findById(bookId);

        bookEntity.changeName(bookUpdateRequest.getName());
        bookEntity.changeContent(bookUpdateRequest.getContent());
        bookEntity.changeLanguage(bookUpdateRequest.getLanguage());
        bookEntity.changeShareStatus(bookUpdateRequest.getShareStatus());
    }

    public void delete(final Long bookId) {
        final BookEntity bookEntity = this.findById(bookId);

        final Boolean isDeleted = bookEntity.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted book.");
        }
        bookEntity.changeIsDeleted(false);
    }

    public BookEntity findById(final Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }
}