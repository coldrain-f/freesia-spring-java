package edu.coldrain.dto;

import edu.coldrain.entity.Book;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookCreateRequest {

    @NotBlank
    private String name;
    private String content;
    @NotBlank
    private String language;
    @NotBlank
    private String shareStatus;

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
