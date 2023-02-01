package edu.coldrain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WordUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String meaning;

    public WordUpdateRequest(final String name, final String meaning) {
        this.name = name;
        this.meaning = meaning;
    }
}
