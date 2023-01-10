package edu.coldrain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WordDetailResponse {

    private final String name;

    @Builder
    public WordDetailResponse(final String name) {
        this.name = name;
    }
}
