package edu.coldrain.service;

import edu.coldrain.dto.BookCreateRequest;
import edu.coldrain.dto.BookResponse;
import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.dto.UserInformation;
import edu.coldrain.entity.Book;
import edu.coldrain.entity.User;
import edu.coldrain.exception.NotFoundUserException;
import edu.coldrain.repository.BookRepository;
import edu.coldrain.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Transactional
    public Long create(final BookCreateRequest request, final UserInformation currentUser) {
        final Book book = request.toEntity();
        final User author = userRepository.findOneWithAuthoritiesByUsername(currentUser.getUsername())
                .orElseThrow(() -> new NotFoundUserException("사용자를 찾을 수 없습니다."));

        book.changeAuthor(author);
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

        // Todo: 중복 코드. 나중에 메서드로 분리 예정
        final Boolean isDeleted = book.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("이미 삭제된 단어장입니다.");
        }
        book.changeIsDeleted(true);
    }

    public Book findById(final Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다. BOOK_ID = " + bookId));

        // Todo: 중복 코드. 나중에 메서드로 분리 예정
        final Boolean isDeleted = book.getIsDeleted();
        if (isDeleted) {
            throw new IllegalArgumentException("이미 삭제된 단어장입니다.");
        }
        return book;
    }

    public Page<Book> findAll(final Pageable pageable) {
        // Todo: 삭제된 단어장은 제외하고 조회하도록 변경 필요
        return bookRepository.findAll(pageable);
    }

    public Page<BookResponse> findAllByQuerydsl(Pageable pageable) {
        return bookRepository.findAllByQuerydsl(pageable);
    }
}