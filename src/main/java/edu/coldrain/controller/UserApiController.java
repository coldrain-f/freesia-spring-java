package edu.coldrain.controller;

import edu.coldrain.dto.UserInformation;
import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * 회원가입 API
     */
    @PostMapping("/users/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signup(@Valid @RequestBody final UserSignUpRequest request) {
        return userService.signup(request);
    }

    /**
     * 현재 로그인한 사용자 정보 조회 API
     */
    @GetMapping("/users/current")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserInformation getCurrentUser() {
        return userService.getCurrentUserWithAuthorities();
    }

    /**
     * 사용자 이름으로 사용자 정보 조회 API - 관리자 용
     */
    @GetMapping("/users/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserInformation getUser(@PathVariable final String username) {
        return userService.getUserWithAuthorities(username);
    }
}