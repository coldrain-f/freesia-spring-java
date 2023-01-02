package edu.coldrain.category.dto;

import lombok.Getter;

@Getter
public class CategoryUpdateRequest {

    private final String name;

    public CategoryUpdateRequest(final String name) {
        this.name = name;
    }
}
