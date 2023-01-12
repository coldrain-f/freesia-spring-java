package edu.coldrain.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class WordUpdateRequest {

    @NotNull
    private final String name;

    public WordUpdateRequest(final String name) {
        this.name = name;
    }
}
