package edu.coldrain.controller;

import edu.coldrain.dto.CategoryCreateRequest;
import edu.coldrain.dto.CategoryResponse;
import edu.coldrain.dto.CategoryUpdateRequest;
import edu.coldrain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    /**
     * 카테고리 등록
     */
    @PostMapping("/books/{id}/categories")
    public Long create(@PathVariable("id") final Long bookId,
                       @RequestBody final CategoryCreateRequest request) {
        return categoryService.create(bookId, request);
    }

    /**
     * 카테고리 수정
     */
    @PatchMapping("/categories/{id}")
    public void update(final @PathVariable("id") Long categoryId, @RequestBody final CategoryUpdateRequest request) {
        categoryService.update(request, categoryId);
    }

    /**
     * 카테고리 삭제
     */
    @DeleteMapping("/categories/{id}")
    public void delete(final @PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
    }

    /**
     * 카테고리 목록 조회
     */
    @GetMapping("/categories")
    public Page<CategoryResponse> findAll(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return null;
    }

}
