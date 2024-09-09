package com.challenger.challengerbe.module.user.service;

import com.challenger.challengerbe.module.user.domain.User;
import com.challenger.challengerbe.module.user.dto.CreateUserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    String registerUser(CreateUserRequest request);
    void checkEmailDuplicate(String email);
    User getUserByIdk(String userToken);
    void generateAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response);
}
