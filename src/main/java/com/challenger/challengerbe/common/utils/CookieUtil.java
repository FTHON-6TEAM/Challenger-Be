package com.challenger.challengerbe.common.utils;

import com.challenger.challengerbe.auth.security.JwtUtil;
import com.challenger.challengerbe.module.user.domain.UserRefreshToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
@Slf4j
public class CookieUtil {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name1 = cookie.getName();
                log.info("refresh Token: " + name1);
                if (name.equals(cookie.getName())) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }

    public void addRefreshTokenCookie(HttpServletResponse response, UserRefreshToken userRefreshToken) {
        String refreshToken = userRefreshToken.getRefreshToken().getRefreshToken();
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Set to true if using HTTPS
        cookie.setPath("/");
//        cookie.setMaxAge(2 * 7 * 24 * 60 * 60); // Set expiration time if needed
        cookie.setMaxAge(5 * 60); // Set expiration time if needed / 2 분
        cookie.setAttribute("SameSite", "Strict"); // Can be "Lax" or "Strict" depending on your requirements
        response.addCookie(cookie);
    }

    public void addAccessTokenRefreshTokenCookie(HttpServletResponse response, UserRefreshToken userRefreshToken, String accessToken) {
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

        String refreshToken = userRefreshToken.getRefreshToken().getRefreshToken();
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Set to true if using HTTPS
        cookie.setPath("/");
//        cookie.setMaxAge(2 * 7 * 24 * 60 * 60); // Set expiration time if needed
        cookie.setMaxAge(5 * 60); // Set expiration time if needed / 2 분
        cookie.setAttribute("SameSite", "Strict"); // Can be "Lax" or "Strict" depending on your requirements
        response.addCookie(cookie);
    }
}

