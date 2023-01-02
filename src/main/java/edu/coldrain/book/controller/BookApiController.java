package edu.coldrain.book.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookApiController {

    /**
     * 단어장 등록
     */
    @PostMapping
    public void create() {

    }

    /**
     * 단어장 수정
     */
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Long bookId) {

    }
    /**
     * 단어장 삭제
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long bookId) {

    }

    /**
     * 단어장 상세 조회
     */
    @GetMapping("/{id}")
    public void findOne(@PathVariable("id") Long bookId) {

    }

    /**
     * 단어장 목록 조회
     */
    @GetMapping
    public void findAll() {

    }
}
