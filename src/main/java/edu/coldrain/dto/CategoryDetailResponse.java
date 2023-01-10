package edu.coldrain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryDetailResponse {

    private final Long id;
    private final String name;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    @QueryProjection
    public CategoryDetailResponse(final Long id, final String name, final LocalDateTime createdAt,
                                  final LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
