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
public class Category extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long completeCount;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Builder
    public Category(final String name, final Long completeCount) {
        this.name = name;
        this.completeCount = completeCount;
        this.isDeleted = false;
    }

    public void changeBookEntity(final Book book) {
        this.book = book;
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public void changeCompleteCount(final Long completeCount) {
        this.completeCount = completeCount;
    }

    public void changeIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}

