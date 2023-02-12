package edu.coldrain.controller;

import edu.coldrain.dto.BookCreateRequest;
import edu.coldrain.dto.BookResponse;
import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.dto.UserInformation;
import edu.coldrain.entity.Book;
import edu.coldrain.service.BookService;
import edu.coldrain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    private final UserService userService;

    /**
     * 단어장 등록 API
     */
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Validated @RequestBody final BookCreateRequest request) {
        return bookService.create(request, userService.getCurrentUserWithAuthorities());
    }

    /**
     * 단어장 수정 API
     */
    @PatchMapping("/books/{id}")
    public void update(final @PathVariable("id") Long bookId,
                       @Validated @RequestBody final BookUpdateRequest request) {
        bookService.update(request, bookId);
    }

    /**
     * 단어장 삭제 API
     */
    @DeleteMapping("/books/{id}")
    public void delete(final @PathVariable("id") Long bookId) {
        bookService.delete(bookId);
    }

    /**
     * 단어장 싱세 조회 API
     */
    @GetMapping("/books/{id}")
    public BookResponse findOne(final @PathVariable("id") Long bookId) {
        final Book book = bookService.findById(bookId);
        return BookResponse.from(book);
    }

    /**
     * 단어장 목록 조회 API
     */
    @GetMapping("/books")
    public Page<BookResponse> findAllByQuerydsl(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return bookService.findAllByQuerydsl(pageable);
    }

    /**
     * 내가 생성한 단어장 목록 조회 API
     */
    @GetMapping("/books/mine")
    public Page<BookResponse> findMyBook(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        final UserInformation currentUserInfo = userService.getCurrentUserWithAuthorities();
        return bookService.findMyBook(pageable, currentUserInfo);
    }
}
