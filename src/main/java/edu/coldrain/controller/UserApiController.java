package edu.coldrain.controller;

import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long signUp(final @RequestBody UserSignUpRequest request) {
        return userService.signUp(request);
    }
}
