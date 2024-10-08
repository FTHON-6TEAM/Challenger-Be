package com.challenger.challengerbe.modules.user.repository;

import com.challenger.challengerbe.modules.user.domain.UserRefreshToken;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    Optional<UserRefreshToken> findByUserIdk(String userIdk);

    UserRefreshToken findByRefreshTokenRefreshToken(String token);

}

