package edu.coldrain.book.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookDetailResponse {

    private final String name;

    private final String content;

    private final String language;

    private final String shareStatus;

    @Builder
    public BookDetailResponse(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
    }
}
