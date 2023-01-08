package edu.coldrain.book.service;

import edu.coldrain.book.dto.BookCreateRequest;
import edu.coldrain.book.dto.BookUpdateRequest;
import edu.coldrain.book.entity.BookEntity;
import edu.coldrain.book.repository.BookRepository;
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
        final BookEntity bookEntity = request.toEntity();
        return bookRepository.save(bookEntity).getId();
    }

    @Transactional
    public void update(final BookUpdateRequest request, final Long bookId) {
        final BookEntity bookEntity = this.findById(bookId);

        bookEntity.changeName(request.getName());
        bookEntity.changeContent(request.getContent());
        bookEntity.changeLanguage(request.getLanguage());
        bookEntity.changeShareStatus(request.getShareStatus());
    }

    @Transactional
    public void delete(final Long bookId) {
        final BookEntity bookEntity = this.findById(bookId);

        final Boolean isDeleted = bookEntity.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted book.");
        }
        bookEntity.changeIsDeleted(true);
    }

    public BookEntity findById(final Long bookId) {
        final BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        final Boolean isDeleted = bookEntity.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("Already deleted book.");
        }
        return bookEntity;
    }

    public Page<BookEntity> findAll(final Pageable pageable) {
        // Todo: 삭제된 단어장은 제외하고 조회하도록 변경 필요
        return bookRepository.findAll(pageable);
    }
}