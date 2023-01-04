package edu.coldrain.word.controller;

import edu.coldrain.word.dto.WordCreateRequest;
import edu.coldrain.word.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordApiController {

    private final WordService wordService;

    /**
     * 단어 등록
     */
    @PostMapping
    public Long create(final WordCreateRequest request) {
        return wordService.create(request);
    }

    /**
     * 단어 수정
     */
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") final Long wordId) {

    }

    /**
     * 단어 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Long wordId) {

    }

    /**
     * 단어 목록 조회
     */
    @GetMapping
    public void findAll(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) final Pageable pageable) {

    }
}
