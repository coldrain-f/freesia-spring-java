package edu.coldrain.controller;

import edu.coldrain.dto.WordCreateRequest;
import edu.coldrain.dto.WordResponse;
import edu.coldrain.dto.WordUpdateRequest;
import edu.coldrain.service.WordService;
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
@Tag(name = "WORD", description = "Word Api Controller")
public class WordApiController {

    private final WordService wordService;

    @Tag(name = "WORD")
    @ApiOperation(value = "단어 등록 API", notes = "", authorizations = {})
    @PostMapping("/categories/{id}/words")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@PathVariable("id") final Long categoryId,
                       @Validated @RequestBody final WordCreateRequest request) {
        return wordService.create(request, categoryId);
    }

    @Tag(name = "WORD")
    @ApiOperation(value = "단어 수정 API", notes = "", authorizations = {})
    @PatchMapping("/words/{id}")
    public void update(@PathVariable("id") final Long wordId,
                       @Validated @RequestBody final WordUpdateRequest request) {
        wordService.update(wordId, request);
    }

    @Tag(name = "WORD")
    @ApiOperation(value = "단어 삭제 API", notes = "", authorizations = {})
    @DeleteMapping("/words/{id}")
    public void delete(@PathVariable("id") final Long wordId) {
        wordService.delete(wordId);
    }

    @Tag(name = "WORD")
    @ApiOperation(value = "단어 목록 조회 API", notes = "", authorizations = {})
    @GetMapping("/words")
    public Page<WordResponse> findAll(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return wordService.findAllByQuerydsl(pageable);
    }

    @Tag(name = "WORD")
    @ApiOperation(value = "특정 카테고리에 소속된 단어 목록 조회 API", notes = "", authorizations = {})
    @GetMapping("/categories/{id}/words")
    public Page<WordResponse> findAllByCategoryId(@PathVariable("id") final Long categoryId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return wordService.findAllByCategoryId(categoryId, pageable);
    }
}
