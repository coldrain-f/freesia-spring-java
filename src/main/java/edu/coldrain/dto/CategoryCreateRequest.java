package edu.coldrain.dto;

import edu.coldrain.entity.Category;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryCreateRequest {

    @NotNull
    private String name;

    @Builder
    public CategoryCreateRequest(final String name) {
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .build();
    }
}
