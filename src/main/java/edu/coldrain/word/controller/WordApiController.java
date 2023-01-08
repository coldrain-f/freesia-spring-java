package edu.coldrain.word.controller;

import edu.coldrain.word.dto.WordCreateRequest;
import edu.coldrain.word.dto.WordDetailResponse;
import edu.coldrain.word.dto.WordUpdateRequest;
import edu.coldrain.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WordApiController {

    private final WordService wordService;

    /**
     * 단어 등록
     */
    @PostMapping("/categories/{id}/words")
    public Long create(@PathVariable("id") final Long categoryId,
                       @RequestBody final WordCreateRequest request) {
        return wordService.create(request, categoryId);
    }

    /**
     * 단어 수정
     */
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") final Long wordId, @RequestBody final WordUpdateRequest request) {
        wordService.update(wordId, request);
    }

    /**
     * 단어 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long wordId) {
        wordService.delete(wordId);
    }

    /**
     * 단어 목록 조회
     */
    @GetMapping
    public Page<WordDetailResponse> findAll(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) final Pageable pageable) {
        return wordService.findAll(pageable)
                .map((wordEntity) -> WordDetailResponse.builder()
                        .name(wordEntity.getName())
                        .build());
    }
}
