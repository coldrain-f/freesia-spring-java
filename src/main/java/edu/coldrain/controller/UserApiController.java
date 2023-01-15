package edu.coldrain.controller;

import edu.coldrain.dto.UserInformation;
import edu.coldrain.dto.UserSignUpRequest;
import edu.coldrain.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "USER", description = "User Api Controller")
public class UserApiController {

    private final UserService userService;

    @Tag(name = "USER")
    @ApiOperation(value = "회원가입 API", notes = "", authorizations = {})
    @PostMapping("/users/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Long signup(@Valid @RequestBody final UserSignUpRequest request) {
        return userService.signup(request);
    }

    @Tag(name = "USER")
    @ApiOperation(value = "로그인한 사용자 정보 조회 API", notes = "", authorizations = {})
    @GetMapping("/users/current")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserInformation getCurrentUser() {
        return userService.getCurrentUserWithAuthorities();
    }

    @Tag(name = "USER")
    @ApiOperation(value = "사용자 정보 조회 API", notes = "", authorizations = {})
    @GetMapping("/users/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserInformation getUser(@PathVariable final String username) {
        return userService.getUserWithAuthorities(username);
    }
}