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
public class Word extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "WORD_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String meaning;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Builder
    public Word(final String name, final String meaning) {
        this.name = name;
        this.meaning = meaning;
        this.isDeleted = false;
    }

    public void changeCategoryEntity(final Category category) {
        this.category = category;
    }
    public void changeName(final String name) {
        this.name = name;
    }

    public void changeMeaning(final String meaning) {
        this.meaning = meaning;
    }

    public void changeIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
