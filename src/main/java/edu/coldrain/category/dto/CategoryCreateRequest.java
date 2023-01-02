package edu.coldrain.category.dto;

import edu.coldrain.category.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryCreateRequest {

    private final String name;

    private final Long completeCount;

    @Builder
    public CategoryCreateRequest(final String name, final Long completeCount) {
        this.name = name;
        this.completeCount = completeCount;
    }

    public CategoryEntity toEntity() {
        return CategoryEntity.builder()
                .name(this.name)
                .completeCount(this.completeCount)
                .build();
    }
}
