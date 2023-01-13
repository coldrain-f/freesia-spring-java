package edu.coldrain.controller;

import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {

    private final UserService userService;

    @PostMapping
    public Long signUp(final UserSignUpRequest request) {
        return userService.signUp(request);
    }
}
