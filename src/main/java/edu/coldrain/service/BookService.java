package edu.coldrain.service;

import edu.coldrain.dto.BookCreateRequest;
import edu.coldrain.dto.BookDetailResponse;
import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.entity.Book;
import edu.coldrain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long create(final BookCreateRequest request) {
        final Book book = request.toEntity();
        return bookRepository.save(book).getId();
    }

    @Transactional
    public void update(final BookUpdateRequest request, final Long bookId) {
        final Book book = this.findById(bookId);

        book.changeName(request.getName());
        book.changeContent(request.getContent());
        book.changeLanguage(request.getLanguage());
        book.changeShareStatus(request.getShareStatus());
    }

    @Transactional
    public void delete(final Long bookId) {
        final Book book = this.findById(bookId);

        final Boolean isDeleted = book.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted book.");
        }
        book.changeIsDeleted(true);
    }

    public Book findById(final Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        final Boolean isDeleted = book.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted book.");
        }
        return book;
    }

    public Page<Book> findAll(final Pageable pageable) {
        // Todo: 삭제된 단어장은 제외하고 조회하도록 변경 필요
        return bookRepository.findAll(pageable);
    }

    public Page<BookDetailResponse> findAllByQuerydsl(Pageable pageable) {
        return bookRepository.findAllByQuerydsl(pageable);
    }
}