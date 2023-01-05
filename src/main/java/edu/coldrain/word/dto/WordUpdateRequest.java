package edu.coldrain.word.dto;

import lombok.Getter;

@Getter
public class WordUpdateRequest {

    private final String name;

    public WordUpdateRequest(final String name) {
        this.name = name;
    }
}
