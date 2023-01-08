package edu.coldrain.category.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryUpdateRequest {

    private String name;

    public CategoryUpdateRequest(final String name) {
        this.name = name;
    }
}
