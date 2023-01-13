package edu.coldrain.repository;

import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // 항상 @Test 보다 먼저 실행 된다.
    @BeforeEach
    void setUp() {
        Book book = Book.builder()
                .name("단어가 읽기다 기본편")
                .content("단어가 읽기다 기본편 - 내용")
                .language("en")
                .shareStatus("public")
                .build();

        this.bookRepository.save(book);
    }

    @Test
    @DisplayName("단어장을 하나 등록한다.")
    void save() {
        // given - 데이터 준비
        Book book = Book.builder()
                .name("단어가 읽기다 기본편")
                .content("단어가 읽기다 기본편 - 내용")
                .language("en")
                .shareStatus("public")
                .build();

        // when - 테스트
        Book savedBook = this.bookRepository.save(book);

        // then - 검증
        assertEquals(book.getName(), savedBook.getName());
        assertEquals(book.getContent(), savedBook.getContent());
        assertEquals(book.getLanguage(), savedBook.getLanguage());
        assertEquals(book.getShareStatus(), savedBook.getShareStatus());
    }

    @Test
    @DisplayName("단어장을 하나 삭제한다.")
    void delete() {
        // given
        Long bookId = 1L;

        // when
        bookRepository.deleteById(bookId);

        // then
        assertFalse(bookRepository.findById(bookId).isPresent());
    }

    @Test
    @DisplayName("단어장을 하나 수정한다.")
    void update() {
        // given
        Long bookId = 1L;
        BookUpdateRequest request = BookUpdateRequest.builder()
                .name("단어가 읽기다 기본편(수정)")
                .content("단어가 읽기다 기본편 - 내용(수정)")
                .language("ko")
                .shareStatus("private")
                .build();

        // when
        Book book = bookRepository.findById(bookId)
                .orElseThrow(IllegalArgumentException::new);

        book.changeName(request.getName());
        book.changeContent(request.getContent());
        book.changeLanguage(request.getLanguage());
        book.changeShareStatus(request.getShareStatus());

        bookRepository.save(book);

        Book changedBook = bookRepository.findById(bookId)
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertEquals(request.getName(), changedBook.getName());
        assertEquals(request.getContent(), changedBook.getContent());
        assertEquals(request.getLanguage(), changedBook.getLanguage());
        assertEquals(request.getShareStatus(), changedBook.getShareStatus());
    }

}