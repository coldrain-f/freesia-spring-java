package edu.coldrain.book.entity;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOOK_ID")
    private Long id;

    @Column()
    private String name;

    @Column()
    private String content;

    @Column()
    private String language;

    @Column()
    private String shareStatus;

    @Column()
    private Boolean isDeleted;

    @Builder
    public BookEntity(final String name, final String content, final String language, final String shareStatus) {
        this.name = name;
        this.content = content;
        this.language = language;
        this.shareStatus = shareStatus;
        this.isDeleted = false;
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public void changeContent(final String content) {
        this.content = content;
    }

    public void changeLanguage(final String language) {
        this.language = language;
    }

    public void changeShareStatus(final String shareStatus) {
        this.shareStatus = shareStatus;
    }

    public void changeIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
