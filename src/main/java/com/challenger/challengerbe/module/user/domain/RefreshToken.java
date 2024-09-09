package com.challenger.challengerbe.module.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.Getter;

@Getter
@Embeddable
public class RefreshToken {
    @Column(name = "refresh_token")
    private String refreshToken;

    public RefreshToken() {
        this.refreshToken = String.valueOf(UUID.randomUUID());
    }
}

