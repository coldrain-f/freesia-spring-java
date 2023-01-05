package edu.coldrain.book.controller;

import edu.coldrain.book.dto.BookCreateRequest;
import edu.coldrain.book.dto.BookDetailResponse;
import edu.coldrain.book.dto.BookUpdateRequest;
import edu.coldrain.book.entity.BookEntity;
import edu.coldrain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/books")
public class BookApiController {

    private final BookService bookService;

    /**
     * 단어장 등록
     */
    @PostMapping
    public Long create(@RequestBody final BookCreateRequest request) {
        log.info("request = {}", request);
        return bookService.create(request);
    }

    /**
     * 단어장 수정
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(final @PathVariable("id") Long bookId, @RequestBody final BookUpdateRequest request) {
        log.info("request = {}", request);
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
    public BookDetailResponse findOne(final @PathVariable("id") Long bookId) {
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
    public Page<BookDetailResponse> findAll(
            // Todo: 추후에 정렬 필드를 createdAt 으로 변경 필요.
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) final Pageable pageable) {

        return bookService.findAll(pageable)
                .map((bookEntity) -> BookDetailResponse.builder()
                        .name(bookEntity.getName())
                        .content(bookEntity.getContent())
                        .language(bookEntity.getLanguage())
                        .shareStatus(bookEntity.getShareStatus())
                        .build());
    }
}
