package edu.coldrain.controller;

import edu.coldrain.dto.UserResponse;
import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/users/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signup(@Valid @RequestBody final UserSignUpRequest request) {
        return userService.signup(request);
    }

    @GetMapping("/users/current")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserResponse getCurrentUser() {
        return userService.getMyUserWithAuthorities();
    }

    @GetMapping("/users/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserResponse getUser(@PathVariable final String username) {
        return userService.getUserWithAuthorities(username);
    }
}