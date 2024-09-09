package com.challenger.challengerbe.auth.security;

import java.util.List;

public record CustomClaims(String userId, List<String> authorities) {
    public static CustomClaims of(String userId, List<String> authorities) {
        return new CustomClaims(userId, authorities);
    }
}

