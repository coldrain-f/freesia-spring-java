package edu.coldrain.word.dto;

import edu.coldrain.word.entity.WordEntity;
import lombok.Getter;

@Getter
public class WordCreateRequest {

    private final String name;

    public WordCreateRequest(final String name) {
        this.name = name;
    }

    public WordEntity toEntity() {
        return WordEntity.builder()
                .name(this.name)
                .build();
    }
}
