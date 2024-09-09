package com.challenger.challengerbe.modules.user.controller;

import com.challenger.challengerbe.modules.user.dto.CreateUserRequest;
import com.challenger.challengerbe.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "유저 api")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody CreateUserRequest request) {
        String userIdk = userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userIdk);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<Void> generateAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        userService.generateAccessTokenByRefreshToken(request, response);

        return ResponseEntity.noContent().build();
    }

}
