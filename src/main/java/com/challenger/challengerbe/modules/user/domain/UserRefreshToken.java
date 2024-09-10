package com.challenger.challengerbe.modules.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_refresh_token")
public class UserRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_idk")
    private String userIdk;

    @Embedded
    private RefreshToken refreshToken;

    public UserRefreshToken(
            String userIdk
    ) {
        this.userIdk = userIdk;
        this.refreshToken = new RefreshToken();
    }

    public void updateRefreshToken() {
        this.refreshToken = new RefreshToken();
    }
}
