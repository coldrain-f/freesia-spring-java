package edu.coldrain.dto;

import edu.coldrain.entity.Word;
import lombok.Getter;

@Getter
public class WordCreateRequest {

    private final String name;

    public WordCreateRequest(final String name) {
        this.name = name;
    }

    public Word toEntity() {
        return Word.builder()
                .name(this.name)
                .build();
    }
}
