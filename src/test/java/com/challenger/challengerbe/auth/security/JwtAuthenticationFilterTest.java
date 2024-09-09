package com.challenger.challengerbe.auth.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.challenger.challengerbe.ControllerTestSupport;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;

@MockBean(JpaMetamodelMappingContext.class)
class JwtAuthenticationFilterTest extends ControllerTestSupport {

    @DisplayName("email, password로 로그인시 인증필터를 거친다.")
    @WithMockUser(username = "as@gmail.com", password = "whdgns0000", roles = "USER")
    @Test
    void testJwtAuthenticationFilter() throws Exception {
        // Given

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil, userRefreshTokenRepository, cookieUtil);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setRequestURI("/api/v1/users/login");
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        request.setContent("{\"email\":\"as@gmail.com\",\"password\":\"whdgns0000\"}".getBytes());

        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        // When
        filter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

}
