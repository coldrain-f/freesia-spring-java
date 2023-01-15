package edu.coldrain.controller;

import edu.coldrain.dto.BookCreateRequest;
import edu.coldrain.dto.BookResponse;
import edu.coldrain.dto.BookUpdateRequest;
import edu.coldrain.entity.Book;
import edu.coldrain.service.BookService;
import edu.coldrain.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "BOOK", description = "Book Api Controller")
public class BookApiController {

    private final BookService bookService;

    private final UserService userService;

    @Tag(name = "BOOK")
    @ApiOperation(value = "단어장 등록 API", notes = "", authorizations = {})
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Validated @RequestBody final BookCreateRequest request) {
        return bookService.create(request, userService.getCurrentUserWithAuthorities());
    }

    @Tag(name = "BOOK")
    @ApiOperation(value = "단어장 수정 API", notes = "", authorizations = {})
    @PatchMapping("/books/{id}")
    public void update(final @PathVariable("id") Long bookId,
                       @Validated @RequestBody final BookUpdateRequest request) {
        bookService.update(request, bookId);
    }

    @Tag(name = "BOOK")
    @ApiOperation(value = "단어장 삭제 API", notes = "", authorizations = {})
    @DeleteMapping("/books/{id}")
    public void delete(final @PathVariable("id") Long bookId) {
        bookService.delete(bookId);
    }

    @Tag(name = "BOOK")
    @ApiOperation(value = "단어장 상세 조회 API", notes = "", authorizations = {})
    @GetMapping("/books/{id}")
    public BookResponse findOne(final @PathVariable("id") Long bookId) {
        final Book book = bookService.findById(bookId);
        return new BookResponse(
                book.getId(), book.getName(), book.getContent(),
                book.getLanguage(), book.getShareStatus(), book.getCreatedAt(),
                book.getModifiedAt());
    }

    @Tag(name = "BOOK")
    @ApiOperation(value = "단어장 목록 조회 API", notes = "", authorizations = {})
    @GetMapping("/books")
    public Page<BookResponse> findAllByQuerydsl(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return bookService.findAllByQuerydsl(pageable);
    }
}
