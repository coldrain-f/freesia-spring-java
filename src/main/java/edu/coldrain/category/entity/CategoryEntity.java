package edu.coldrain.category.entity;

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
    private Long id;

    @Column()
    private String name;

    @Column()
    private Long completeCount;

    @Builder
    public CategoryEntity(final String name, final Long completeCount) {
        this.name = name;
        this.completeCount = completeCount;
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public void changeCompleteCount(final Long completeCount) {
        this.completeCount = completeCount;
    }
}

