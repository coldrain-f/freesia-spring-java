package edu.coldrain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    /**
     * 카테고리 등록
     */
    public void create() {

    }

    /**
     * 카테고리 수정
     */
    public void update(final @PathVariable("id") Long categoryId) {

    }

    /**
     * 카테고리 삭제
     */
    public void delete(final @PathVariable("id") Long categoryId) {

    }

    /**
     * 카테고리 목록 조회
     */
    public void findAll(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.DESC) final Pageable pageable) {

    }

}
