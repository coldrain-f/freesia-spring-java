package edu.coldrain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class BookResponse {

    private final Long id;

    private final String name;

    private final String content;

    private final String language;

    private final String shareStatus;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    @QueryProjection
    public BookResponse(final Long id, final String name, final String content, final String language,
                        final String shareStatus, final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
