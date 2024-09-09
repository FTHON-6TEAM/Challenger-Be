package com.challenger.challengerbe.auth.security;

import com.challenger.challengerbe.common.utils.ApiResponseDto;
import com.challenger.challengerbe.common.utils.CookieUtil;
import com.challenger.challengerbe.module.user.controller.LoginRequest;
import com.challenger.challengerbe.module.user.domain.UserRefreshToken;
import com.challenger.challengerbe.module.user.repository.UserRefreshTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j(topic = "로그인, JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final CookieUtil cookieUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRefreshTokenRepository userRefreshTokenRepository, CookieUtil cookieUtil) {
        this.jwtUtil = jwtUtil;
        this.userRefreshTokenRepository = userRefreshTokenRepository;
        this.cookieUtil = cookieUtil;
        setFilterProcessesUrl("/api/v1/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(),
                    LoginRequest.class);
            List<GrantedAuthority> authorities = getAuthorities(List.of("ROLE_USER"));

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword(),
                            authorities
//                            null
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<GrantedAuthority> getAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authentication) throws IOException {
        UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());

        // Jwt token 생성 access token 생성
        String token = jwtUtil.createToken(userDetails.getUserIdk(), userDetails.getEmail());

        handleLoginSuccess(response, userDetails, token);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        String result = new ObjectMapper().writeValueAsString(
                new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), "login failure")
        );

        response.getOutputStream().print(result);
    }

    private void handleLoginSuccess(HttpServletResponse response, UserDetailsImpl userDetails, String token) throws IOException {
        UserRefreshToken userRefreshToken = getOrGenerate(userDetails.getUserIdk());

        cookieUtil.addRefreshTokenCookie(response, userRefreshToken);

        // Add JWT token in the Authorization header
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        // Set response status and content type
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
    }

    private UserRefreshToken getOrGenerate(String userIdk) {
        Optional<UserRefreshToken> userRefreshToken = userRefreshTokenRepository.findByUserIdk(userIdk);

        if (userRefreshToken.isPresent()) {
            log.info("Before userRefreshToken" + userRefreshToken.get().getRefreshToken().getRefreshToken());
            userRefreshToken.get().updateRefreshToken();
            log.info("After userRefreshToken" + userRefreshToken.get().getRefreshToken().getRefreshToken());
            userRefreshTokenRepository.save(userRefreshToken.get());

            return userRefreshToken.get();
        } else {
            // refresh Token 생성 저장
            UserRefreshToken newUserRefreshToken = new UserRefreshToken(userIdk);
            log.info("generate RefreshToken value: " + newUserRefreshToken.getRefreshToken().getRefreshToken());
            userRefreshTokenRepository.save(newUserRefreshToken);

            return newUserRefreshToken;
        }
    }
}
