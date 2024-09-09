package com.challenger.challengerbe;

import com.challenger.challengerbe.auth.security.JwtUtil;
import com.challenger.challengerbe.common.utils.CookieUtil;
import com.challenger.challengerbe.module.user.controller.UserController;
import com.challenger.challengerbe.module.user.repository.UserRefreshTokenRepository;
import com.challenger.challengerbe.module.user.repository.UserRepository;
import com.challenger.challengerbe.module.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        UserController.class
})
public abstract class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected UserService userService;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected UserRefreshTokenRepository userRefreshTokenRepository;

    @MockBean
    protected JwtUtil jwtUtil;

    @MockBean
    protected CookieUtil cookieUtil;
}

