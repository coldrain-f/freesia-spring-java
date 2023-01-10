package edu.coldrain.category.dto;

import edu.coldrain.category.entity.Category;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryCreateRequest {

    private String name;

    @Builder
    public CategoryCreateRequest(final String name) {
        this.name = name;
    }

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .completeCount(0L)
                .build();
    }
}
