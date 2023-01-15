package edu.coldrain.controller;

import edu.coldrain.dto.BookCreateRequest;
import edu.coldrain.dto.BookResponse;
import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.entity.Book;
import edu.coldrain.service.BookService;
import edu.coldrain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BookApiController {

    private final BookService bookService;

    private final UserService userService;

    /**
     * 단어장 등록
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Validated @RequestBody final BookCreateRequest request) {
        return bookService.create(request, userService.getCurrentUserWithAuthorities());
    }

    /**
     * 단어장 수정
     */
    @PatchMapping("/{id}")
    public void update(final @PathVariable("id") Long bookId,
                       @Validated @RequestBody final BookUpdateRequest request) {
        bookService.update(request, bookId);
    }

    /**
     * 단어장 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(final @PathVariable("id") Long bookId) {
        bookService.delete(bookId);
    }

    /**
     * 단어장 상세 조회
     */
    @GetMapping("/{id}")
    public BookResponse findOne(final @PathVariable("id") Long bookId) {
        final Book book = bookService.findById(bookId);
        return new BookResponse(
                book.getId(), book.getName(), book.getContent(),
                book.getLanguage(), book.getShareStatus(), book.getCreatedAt(),
                book.getModifiedAt());
    }

    /**
     * 단어장 목록 조회 QueryDSL
     */
    @GetMapping
    public Page<BookResponse> findAllByQuerydsl(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return bookService.findAllByQuerydsl(pageable);
    }
}
