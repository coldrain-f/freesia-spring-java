package edu.coldrain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import edu.coldrain.common.TimestampedResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WordResponse extends TimestampedResponse {

    private final Long id;
    private final String name;

    @QueryProjection
    public WordResponse(final Long id, final String name, final LocalDateTime createdAt,
                        final LocalDateTime modifiedAt) {
        this. id = id;
        this.name = name;
        this.setCreatedAt(createdAt);
        this.setModifiedAt(modifiedAt);
    }
}
