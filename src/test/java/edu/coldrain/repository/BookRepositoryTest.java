package edu.coldrain.repository;

import edu.coldrain.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

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
        Book savedBook = bookRepository.save(book);

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
        Book book = Book.builder()
                .name("단어가 읽기다 기본편")
                .content("단어가 읽기다 기본편 - 내용")
                .language("en")
                .shareStatus("public")
                .build();

        Long savedBookId = bookRepository.save(book).getId();

        // when
        bookRepository.deleteById(savedBookId);
        Optional<Book> findBook = bookRepository.findById(savedBookId);

        // then
        assertFalse(findBook.isPresent());
    }
}