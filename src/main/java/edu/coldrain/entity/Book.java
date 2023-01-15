package edu.coldrain.entity;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOOK_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(nullable = false)
    private String name;

    @Column
    private String content;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String shareStatus;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Builder
    public Book(final String name, final String content, final String language, final String shareStatus) {
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

    public void changeAuthor(final User author) {
        this.author = author;
    }
}
