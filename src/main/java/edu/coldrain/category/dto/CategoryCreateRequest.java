package edu.coldrain.category.dto;

import edu.coldrain.category.entity.CategoryEntity;
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

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .name(this.name)
                .completeCount(0L)
                .build();
    }
}
