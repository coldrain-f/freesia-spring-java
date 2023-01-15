package edu.coldrain.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorityResponse {

    private String authorityName;

    @Builder
    public AuthorityResponse(final String authorityName) {
        this.authorityName = authorityName;
    }
}
