package edu.coldrain.dto;

import edu.coldrain.entity.Word;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WordCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String meaning;

    @Builder
    public WordCreateRequest(final String name, final String meaning) {
        this.name = name;
        this.meaning = meaning;
    }

    public Word toEntity() {
        return Word.builder()
                .name(this.name)
                .meaning(this.meaning)
                .build();
    }
}
