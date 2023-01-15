package edu.coldrain.service;

import edu.coldrain.dto.UserResponse;
import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.entity.Authority;
import edu.coldrain.entity.User;
import edu.coldrain.exception.DuplicateUserException;
import edu.coldrain.exception.NotFoundUserException;
import edu.coldrain.repository.UserRepository;
import edu.coldrain.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signup(UserSignUpRequest request) {
        if (userRepository.findOneWithAuthoritiesByUsername(request.getUsername()).orElse(null) != null) {
            throw new DuplicateUserException("이미 가입되어 있는 유저입니다.");
        }

        final Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        final User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user).getId();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserWithAuthorities(final String username) {
        return UserResponse.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserResponse getMyUserWithAuthorities() {
        return UserResponse.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundUserException("사용자를 찾을 수 없습니다."))
        );
    }
}
