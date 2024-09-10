package com.challenger.challengerbe.modules.user.service;

import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.dto.UserCreateRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    String insertUser(UserCreateRequest request);
    void checkEmailDuplicate(String email);
    User getUserByIdk(String userToken);
    void generateAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response);
}
