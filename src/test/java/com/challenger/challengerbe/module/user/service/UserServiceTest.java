package com.challenger.challengerbe.module.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.domain.User.Role;
import com.challenger.challengerbe.modules.user.domain.UserRefreshToken;
import com.challenger.challengerbe.modules.user.dto.CreateUserRequest;
import com.challenger.challengerbe.modules.user.repository.UserRefreshTokenRepository;
import com.challenger.challengerbe.modules.user.repository.UserRepository;
import com.challenger.challengerbe.modules.user.service.UserService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRefreshTokenRepository userRefreshTokenRepository;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @DisplayName("회원가입 유저를 등록한다.")
    @Test
    void registerUser() {
        // given
        String email = "jonghuncu@gmail.com";
        String password = "password";
        String username = "name";
        String role = "admin";
        CreateUserRequest request = new CreateUserRequest("idk", email, password, username, role);

        // when
        String userIdk = userService.registerUser(request);
        User user = userRepository.findById(userIdk).orElseThrow();

        // then
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getRole()).isEqualTo(Role.ROLE_ADMIN);
    }

    @DisplayName("RefreshToken이 없는 경우 예외를 발생시킨다")
    @Test
    void EmptyRefreshTokengenerateAccessTokenByRefreshToken() {
        // given
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        Cookie mockCookie = new Cookie("refreshToken", null);
        request.setCookies(mockCookie);

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> userService.generateAccessTokenByRefreshToken(request, response));
    }

    @DisplayName("RefreshToken이 유효한 경우 AccessToken을 재발급한다")
    @Test
    void generateAccessTokenByRefreshToken() {
        // given
        String email = "jonghuncu@gmail.com";
        String password = "password";
        String username = "name";
        String role = "admin";
        CreateUserRequest createRequest = new CreateUserRequest("idk", email, password, username, role);

        String userIdk = userService.registerUser(createRequest);

        // refresh token
        UserRefreshToken userRefreshToken = new UserRefreshToken(userIdk);
        userRefreshTokenRepository.save(userRefreshToken);

        String refreshToken = userRefreshToken.getRefreshToken().getRefreshToken();

        // cookie 세팅
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        Cookie mockCookie = new Cookie("refreshToken", refreshToken);
        request.setCookies(mockCookie);

        // when
        userService.generateAccessTokenByRefreshToken(request, response);

        // then
        String accessToken = response.getHeader("Authorization");  // Assuming the token is in the "Authorization" header
        assertThat(accessToken).isNotNull();
        assertThat(accessToken).startsWith("Bearer ");
    }

    @DisplayName("유효하지 않은 RefreshToken인 경우 예외를 발생시킨다")
    @Test
    void invalidRefreshTokengenerateAccessTokenByRefreshToken() {
        // given
        String email = "jonghuncu@gmail.com";
        String password = "password";
        String username = "name";
        String role = "admin";
        CreateUserRequest createRequest = new CreateUserRequest("idk", email, password, username, role);

        String userIdk = userService.registerUser(createRequest);

        // refresh token
        UserRefreshToken userRefreshToken = new UserRefreshToken(userIdk);
        userRefreshTokenRepository.save(userRefreshToken);

        String refreshToken = userRefreshToken.getRefreshToken().getRefreshToken();
        String invalidRefreshToken = refreshToken + "invalid";

        // cookie 세팅
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        Cookie mockCookie = new Cookie("refreshToken", invalidRefreshToken);
        request.setCookies(mockCookie);

        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> userService.generateAccessTokenByRefreshToken(request, response));
    }
}
