package edu.coldrain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class WordUpdateRequest {

    @NotBlank
    private final String name;

    public WordUpdateRequest(final String name) {
        this.name = name;
    }
}
