package edu.coldrain.book.controller;

import edu.coldrain.book.dto.BookCreateRequest;
import edu.coldrain.book.dto.BookDetailResponse;
import edu.coldrain.book.dto.BookUpdateRequest;
import edu.coldrain.book.entity.BookEntity;
import edu.coldrain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookApiController {

    private final BookService bookService;

    /**
     * 단어장 등록
     */
    @PostMapping
    public Long create(final BookCreateRequest bookCreateRequest) {
        return bookService.create(bookCreateRequest);
    }

    /**
     * 단어장 수정
     */
    @PatchMapping("/{id}")
    public void update(final @PathVariable("id") Long bookId, final BookUpdateRequest request) {
        bookService.update(request, bookId);
    }

    /**
     * 단어장 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long bookId) {
        bookService.delete(bookId);
    }

    /**
     * 단어장 상세 조회
     */
    @GetMapping("/{id}")
    public BookDetailResponse findOne(@PathVariable("id") Long bookId) {
        final BookEntity bookEntity = bookService.findById(bookId);
        return BookDetailResponse.builder()
                .name(bookEntity.getName())
                .content(bookEntity.getContent())
                .language(bookEntity.getLanguage())
                .shareStatus(bookEntity.getShareStatus())
                .build();
    }

    /**
     * 단어장 목록 조회
     */
    @GetMapping
    public void findAll() {

    }
}
