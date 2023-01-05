package edu.coldrain.book.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookUpdateRequest {

    private final String name;
    private final String content;
    private final String language;
    private final String shareStatus;

    public BookUpdateRequest(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
    }
}
