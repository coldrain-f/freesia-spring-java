package edu.coldrain.word.entity;

import jakarta.persistence.*;
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
    private Long id;

    @Column()
    private String name;

    @Column()
    private Boolean isDeleted;

    @Builder
    public WordEntity(final String name) {
        this.name = name;
        this.isDeleted = false;
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public void changeIsDeleted(final Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
