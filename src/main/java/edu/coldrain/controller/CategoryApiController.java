package edu.coldrain.controller;

import edu.coldrain.dto.CategoryCreateRequest;
import edu.coldrain.dto.CategoryResponse;
import edu.coldrain.dto.CategoryUpdateRequest;
import edu.coldrain.service.CategoryService;
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
@Tag(name = "CATEGORY", description = "Category Api Controller")
public class CategoryApiController {

    private final CategoryService categoryService;

    @Tag(name = "CATEGORY")
    @ApiOperation(value = "카테고리 등록 API", notes = "", authorizations = {})
    @PostMapping("/books/{id}/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@PathVariable("id") final Long bookId,
                       @Validated @RequestBody final CategoryCreateRequest request) {
        return categoryService.create(bookId, request);
    }

    @Tag(name = "CATEGORY")
    @ApiOperation(value = "카테고리 수정 API", notes = "", authorizations = {})
    @PatchMapping("/categories/{id}")
    public void update(final @PathVariable("id") Long categoryId,
                       @Validated @RequestBody final CategoryUpdateRequest request) {
        categoryService.update(request, categoryId);
    }

    @Tag(name = "CATEGORY")
    @ApiOperation(value = "카테고리 삭제 API", notes = "", authorizations = {})
    @DeleteMapping("/categories/{id}")
    public void delete(final @PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
    }

    @Tag(name = "CATEGORY")
    @ApiOperation(value = "카테고리 목록 조회 API", notes = "", authorizations = {})
    @GetMapping("/categories")
    public Page<CategoryResponse> findAllByQuerydsl(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return categoryService.findAllByQuerydsl(pageable);
    }

    @Tag(name = "CATEGORY")
    @ApiOperation(value = "특정 단어장에 소속된 카테고리 목록 조회 API", notes = "", authorizations = {})
    @GetMapping("/books/{id}/categories")
    public Page<CategoryResponse> findAllByBookId(
            @PathVariable("id") final Long bookId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return categoryService.findAllByBookId(bookId, pageable);
    }

}
