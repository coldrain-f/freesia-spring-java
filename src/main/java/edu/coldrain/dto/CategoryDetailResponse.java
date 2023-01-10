package edu.coldrain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryDetailResponse {

    private final String name;

    @Builder
    public CategoryDetailResponse(final String name) {
        this.name = name;
    }
}
