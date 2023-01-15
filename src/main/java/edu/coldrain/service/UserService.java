package edu.coldrain.service;

import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.entity.User;
import edu.coldrain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(final UserSignUpRequest request) {
        final User user = request.toEntity();
        final String rawPassword = user.getPassword();
        final String encodedPassword = passwordEncoder.encode(rawPassword);

        user.changePassword(encodedPassword);

        return userRepository.save(user).getId();
    }
}
