package com.challenger.challengerbe.modules.user.service;

import com.challenger.challengerbe.auth.security.CustomPasswordEncoder;
import com.challenger.challengerbe.auth.security.JwtUtil;
import com.challenger.challengerbe.common.utils.CookieUtil;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.domain.User.Role;
import com.challenger.challengerbe.modules.user.domain.UserRefreshToken;
import com.challenger.challengerbe.modules.user.dto.UserCreateRequest;
import com.challenger.challengerbe.modules.user.repository.UserRefreshTokenRepository;
import com.challenger.challengerbe.modules.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final CustomPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Transactional
    @Override
    public String insertUser(UserCreateRequest request) {
        Role role = Role.ROLE_USER;
        if (request.role().equals("admin")) role = Role.ROLE_ADMIN;

        User user = User.builder()
                .idk(request.idk())
                .email(request.email())
                .password(request.password())
                .username(request.username())
                .passwordEncoder(passwordEncoder)
                .role(role)
                .build();
        userRepository.save(user);

        return user.getIdk();
    }

    @Override
    public void checkEmailDuplicate(String email) {
        boolean isEmailDuplicated = userRepository.existsByEmail(email);
        if (isEmailDuplicated) {
            throw new IllegalArgumentException("checkEmailDuplicate >> 유저 email: " + email + "은 이미 존재합니다.");
        }
    }

    @Override
    public User selectUserByIdk(String userIdk) {
        return userRepository.findById(userIdk).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    // refreshToken이 유효하다면
    // accessToken, refreshToken 재발급 진행
    @Transactional
    @Override
    public void generateAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> refreshTokenCookie = CookieUtil.getCookie(request, "refreshToken");

        if (refreshTokenCookie.isEmpty()) {
            throw new IllegalArgumentException("empty() refreshToken 입니다.");
        }
        String refreshToken = refreshTokenCookie.get().getValue();
        log.info("existing refreshToken value: " + refreshToken);
        UserRefreshToken getRefreshToken = userRefreshTokenRepository.findByRefreshTokenRefreshToken(refreshToken);

        if (getRefreshToken == null) {
            throw new IllegalArgumentException("유효하지 않은 refreshToken 입니다.");
        }
        getRefreshToken.updateRefreshToken();
        log.info("new refreshToken value: " + getRefreshToken.getRefreshToken().getRefreshToken());

        // token으로 변경되어야 함
        String userIdk = getRefreshToken.getUserIdk();

        User user = selectUserByIdk(userIdk);
        String email = user.getEmail();
        String accessToken = jwtUtil.createToken(user.getIdk(), email);
        log.info("refreshToken save success");
        cookieUtil.addAccessTokenRefreshTokenCookie(response, getRefreshToken, accessToken);
        log.info("add refreshToken cookie");
    }
}
