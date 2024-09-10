package com.challenger.challengerbe.modules.user.controller;

import com.challenger.challengerbe.modules.user.dto.UserCreateRequest;
import com.challenger.challengerbe.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "유저를 등록합니다.", description = "@RequestBody를 활용한 POST Method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공",
            content = {@Content(schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "유저 등록에 실패하였습니다.")
    })
    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserCreateRequest request) {
        String userIdk = userService.insertUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userIdk);
    }

    @Operation(summary = "refreshToken으로 accessToken을 생성합니다.", description = "Cookie의 refreshToken을 통한 POST Method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "토큰 생성에 실패하였습니다.")
    })
    @PostMapping(value = "/refresh-token")
    public ResponseEntity<Void> generateAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        userService.generateAccessTokenByRefreshToken(request, response);

        return ResponseEntity.noContent().build();
    }

}
