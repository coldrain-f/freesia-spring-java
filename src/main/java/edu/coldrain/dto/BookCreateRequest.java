package edu.coldrain.dto;

import edu.coldrain.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class BookCreateRequest {

    @NotBlank
    private final String name;
    private final String content;
    @NotBlank
    private final String language;
    @NotBlank
    private final String shareStatus;

    @Builder
    public BookCreateRequest(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
    }

    public Book toEntity() {
        return Book.builder()
                .name(this.name)
                .content(this.content)
                .language(this.language)
                .shareStatus(this.shareStatus)
                .build();
    }
}
