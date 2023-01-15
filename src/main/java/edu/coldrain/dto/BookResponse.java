package edu.coldrain.dto;

import com.querydsl.core.annotations.QueryProjection;
import edu.coldrain.entity.Book;
import lombok.Builder;
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
    @Builder
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

    public static BookResponse from(final Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .content(book.getContent())
                .language(book.getLanguage())
                .shareStatus(book.getShareStatus())
                .createdAt(book.getCreatedAt())
                .modifiedAt(book.getModifiedAt())
                .build();
    }
}
