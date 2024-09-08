package com.challenger.challengerbe.module.user.domain;

import com.challenger.challengerbe.auth.security.CustomPasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.text.MessageFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPassword {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 18;

    @Column(name = "password")
    private String password;

    public UserPassword(String rawPassword, CustomPasswordEncoder passwordEncoder) {
        validateNotNull(rawPassword);
        validateUserPasswordLength(rawPassword);
        this.password = passwordEncoder.encodePassword(rawPassword);
    }

    private void validateNotNull(String password) {
        if (password == null) {
            throw new IllegalArgumentException("패스워드는 필수값입니다.");
        }
    }

    private void validateUserPasswordLength(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            throw new IllegalArgumentException(
                    MessageFormat.format("패스워드는 {0}자 이상, {1}자 이하여야 합니다.",
                            MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH));
        }
    }
}
