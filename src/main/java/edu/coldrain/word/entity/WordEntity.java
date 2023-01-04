package edu.coldrain.word.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String name;

    @Builder
    public WordEntity(final String name) {
        this.name = name;
    }

    public void changeName(final String name) {
        this.name = name;
    }
}
