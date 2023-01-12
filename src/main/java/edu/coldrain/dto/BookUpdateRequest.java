package edu.coldrain.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class BookUpdateRequest {

    @NotBlank
    private final String name;
    @NotBlank
    private final String content;
    @NotBlank
    private final String language;
    @NotBlank
    private final String shareStatus;

    public BookUpdateRequest(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
    }
}
