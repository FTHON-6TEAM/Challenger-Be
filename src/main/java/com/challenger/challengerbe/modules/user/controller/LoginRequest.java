package com.challenger.challengerbe.modules.user.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {
    private String idk;
    private String email;
    private String username;

    public LoginRequest(String idk, String email, String username) {
        this.idk = idk;
        this.email = email;
        this.username = username;
    }
}
