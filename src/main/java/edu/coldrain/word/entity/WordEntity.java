package edu.coldrain.word.entity;

import edu.coldrain.category.entity.CategoryEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "WORD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity categoryEntity;

    @Column()
    private String name;

    @Column()
    private Boolean isDeleted;

    @Builder
    public WordEntity(final String name) {
        this.name = name;
        this.isDeleted = false;
    }

    public void changeCategoryEntity(final CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
    public void changeName(final String name) {
        this.name = name;
    }

    public void changeIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
