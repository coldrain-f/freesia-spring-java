package edu.coldrain.dto;

import com.querydsl.core.annotations.QueryProjection;
import edu.coldrain.common.TimestampedResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WordResponse extends TimestampedResponse {

    private final Long id;
    private final String name;

    private final String meaning;

    @QueryProjection
    public WordResponse(final Long id, final String name, final String meaning,
                        final LocalDateTime createdAt, final LocalDateTime modifiedAt) {
        this. id = id;
        this.name = name;
        this.meaning = meaning;
        this.setCreatedAt(createdAt);
        this.setModifiedAt(modifiedAt);
    }
}
