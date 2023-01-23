package edu.coldrain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookUpdateRequest {

    @NotBlank
    private String name;
    @NotNull
    private String content;
    @NotBlank
    private String language;
    @NotBlank
    private String shareStatus;

    @Builder
    public BookUpdateRequest(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
    }
}
