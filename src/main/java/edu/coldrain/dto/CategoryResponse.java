package edu.coldrain.dto;

import com.querydsl.core.annotations.QueryProjection;
import edu.coldrain.common.TimestampedResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryResponse extends TimestampedResponse {

    private final Long id;
    private final String name;

    @Builder
    @QueryProjection
    public CategoryResponse(final Long id, final String name, final LocalDateTime createdAt,
                            final LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.setCreatedAt(createdAt);
        this.setModifiedAt(modifiedAt);
    }
}
