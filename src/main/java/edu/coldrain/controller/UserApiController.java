package edu.coldrain.controller;

import edu.coldrain.dto.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {

    @PostMapping
    public void signUp(final UserSignUpRequest request) {

    }
}
