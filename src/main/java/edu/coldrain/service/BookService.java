package edu.coldrain.service;

import edu.coldrain.dto.BookCreateRequest;
import edu.coldrain.dto.BookResponse;
import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.dto.UserInformation;
import edu.coldrain.entity.Book;
import edu.coldrain.entity.Learn;
import edu.coldrain.entity.User;
import edu.coldrain.exception.NotFoundUserException;
import edu.coldrain.repository.BookRepository;
import edu.coldrain.repository.LearnRepository;
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

    private final LearnRepository learnRepository;

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

    public Page<BookResponse> findAllByQuerydsl(final Pageable pageable) {
        return bookRepository.findAllByQuerydsl(pageable);
    }

    public Page<BookResponse> findMyBook(final Pageable pageable, final UserInformation currentUserInfo) {
        final String currentUsername = currentUserInfo.getUsername();
        final User currentUser = userRepository.findOneWithAuthoritiesByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundUserException("사용자를 찾을 수 없습니다."));

        return bookRepository.findMyBook(pageable, currentUser.getId());
    }

    @Transactional
    public Long learn(final Long bookId, final UserInformation currentUserInfo) {
        final User currentUser = userRepository.findOneWithAuthoritiesByUsername(currentUserInfo.getUsername())
                .orElseThrow(() -> new NotFoundUserException("사용자를 찾을 수 없습니다."));
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        // 사용자 번호와 단어장 번호로 조회해서 있으면 예외 발생 - 이미 학습중인데 학습하려고 했기 때문.
        learnRepository.findByUserIdAndBookId(currentUser.getId(), book.getId());

        final Learn learn = Learn.builder()
                .user(currentUser)
                .book(book)
                .build();

        final Learn savedLearn = learnRepository.save(learn);
        return savedLearn.getId();
    }
}