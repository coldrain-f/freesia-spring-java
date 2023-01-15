package edu.coldrain.handler;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomFieldError {

    private String field;

    private String rejectedValue;

    private String reason;

    @Builder
    public CustomFieldError(final String field, final String rejectedValue, final String reason) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.reason = reason;
    }
}
