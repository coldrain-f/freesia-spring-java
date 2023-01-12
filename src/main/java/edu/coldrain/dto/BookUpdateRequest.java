package edu.coldrain.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class BookUpdateRequest {

    @NotNull
    private final String name;
    @NotNull
    private final String content;
    @NotNull
    private final String language;
    @NotNull
    private final String shareStatus;

    public BookUpdateRequest(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
    }
}
