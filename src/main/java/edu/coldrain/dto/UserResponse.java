package edu.coldrain.dto;

import edu.coldrain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private String username;

    private String nickname;

    private Set<AuthorityResponse> authorityResponseSet;

    @Builder
    public UserResponse(final String username, final String nickname, final Set<AuthorityResponse> authorityResponseSet) {
        this.username = username;
        this.nickname = nickname;
        this.authorityResponseSet = authorityResponseSet;
    }

    public static UserResponse from(User user) {
        if(user == null) return null;

        return UserResponse.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .authorityResponseSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityResponse.builder()
                                .authorityName(authority.getAuthorityName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
