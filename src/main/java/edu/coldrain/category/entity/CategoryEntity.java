package edu.coldrain.category.entity;

import edu.coldrain.book.entity.BookEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private BookEntity bookEntity;

    @Column()
    private String name;

    @Column()
    private Long completeCount;

    @Column()
    private Boolean isDeleted;

    @Builder
    public CategoryEntity(final String name, final Long completeCount) {
        this.name = name;
        this.completeCount = completeCount;
    }

    public void changeBookEntity(final BookEntity bookEntity) {
        this.bookEntity = bookEntity;
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

