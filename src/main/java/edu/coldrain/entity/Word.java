package edu.coldrain.entity;

import edu.coldrain.entity.Category;
import javax.persistence.*;

import edu.coldrain.common.Timestamped;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Word extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "WORD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column()
    private String name;

    @Column()
    private Boolean isDeleted;

    @Builder
    public Word(final String name) {
        this.name = name;
        this.isDeleted = false;
    }

    public void changeCategoryEntity(final Category category) {
        this.category = category;
    }
    public void changeName(final String name) {
        this.name = name;
    }

    public void changeIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}