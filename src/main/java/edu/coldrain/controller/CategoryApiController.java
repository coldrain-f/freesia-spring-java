package edu.coldrain.controller;

import edu.coldrain.dto.CategoryCreateRequest;
import edu.coldrain.dto.CategoryResponse;
import edu.coldrain.dto.CategoryUpdateRequest;
import edu.coldrain.entity.Category;
import edu.coldrain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    /**
     * 특정 단어장에 카테고리 등록 API
     */
    @PostMapping("/books/{id}/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@PathVariable("id") final Long bookId,
                       @Validated @RequestBody final CategoryCreateRequest request) {
        return categoryService.create(bookId, request);
    }

    /**
     * 카테고리 수정 API
     */
    @PatchMapping("/categories/{id}")
    public void update(final @PathVariable("id") Long categoryId,
                       @Validated @RequestBody final CategoryUpdateRequest request) {
        categoryService.update(request, categoryId);
    }

    /**
     * 카테고리 삭제 API
     */
    @DeleteMapping("/categories/{id}")
    public void delete(final @PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
    }

    /**
     * 카테고리 목록 조회 API
     */
    @GetMapping("/categories")
    public Page<CategoryResponse> findAllByQuerydsl(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return categoryService.findAllByQuerydsl(pageable);
    }

    /**
     * 카테고리 상세 조회 API
     */
    @GetMapping("/categories/{id}")
    public CategoryResponse findById(@PathVariable("id") final Long categoryId) {
        final Category category = categoryService.findById(categoryId);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    /**
     * 특정 단어장에 소속된 카테고리 목록 조회 API
     */
    @GetMapping("/books/{id}/categories")
    public Page<CategoryResponse> findAllByBookId(
            @PathVariable("id") final Long bookId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return categoryService.findAllByBookId(bookId, pageable);
    }
}
