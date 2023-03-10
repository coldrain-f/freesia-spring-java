package edu.coldrain.controller;

import edu.coldrain.dto.WordCreateRequest;
import edu.coldrain.dto.WordResponse;
import edu.coldrain.dto.WordUpdateRequest;
import edu.coldrain.service.WordService;
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
public class WordApiController {

    private final WordService wordService;

    /**
     * 특정 카테고리에 단어 등록 API
     */
    @PostMapping("/categories/{id}/words")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@PathVariable("id") final Long categoryId,
                       @Validated @RequestBody final WordCreateRequest request) {
        log.info("특정 카테고리에 단어 등록 API");
        return wordService.create(request, categoryId);
    }

    /**
     * 단어 수정 API
     */
    @PatchMapping("/words/{id}")
    public void update(@PathVariable("id") final Long wordId,
                       @Validated @RequestBody final WordUpdateRequest request) {
        wordService.update(wordId, request);
    }

    /**
     * 단어 삭제 API
     */
    @DeleteMapping("/words/{id}")
    public void delete(@PathVariable("id") final Long wordId) {
        wordService.delete(wordId);
    }

    /**
     * 단어 목록 조회 API
     */
    @GetMapping("/words")
    public Page<WordResponse> findAll(
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return wordService.findAllByQuerydsl(pageable);
    }

    /**
     * 특정 카테고리에 소속된 단어 목록 조회 API
     */
    @GetMapping("/categories/{id}/words")
    public Page<WordResponse> findAllByCategoryId(@PathVariable("id") final Long categoryId,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) final Pageable pageable) {
        return wordService.findAllByCategoryId(categoryId, pageable);
    }
}
