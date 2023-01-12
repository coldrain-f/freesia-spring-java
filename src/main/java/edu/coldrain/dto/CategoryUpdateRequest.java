package edu.coldrain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryUpdateRequest {

    @NotBlank
    private String name;

    public CategoryUpdateRequest(final String name) {
        this.name = name;
    }
}
