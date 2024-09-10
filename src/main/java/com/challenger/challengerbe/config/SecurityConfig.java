package com.challenger.challengerbe.config;

import com.challenger.challengerbe.auth.security.BCryptCustomPasswordEncoder;
import com.challenger.challengerbe.auth.security.CustomPasswordEncoder;
import com.challenger.challengerbe.auth.security.JwtAuthenticationFilter;
import com.challenger.challengerbe.auth.security.JwtAuthenticationProvider;
import com.challenger.challengerbe.auth.security.JwtAuthorizationFilter;
import com.challenger.challengerbe.auth.security.JwtUtil;
import com.challenger.challengerbe.auth.security.UserDetailsServiceImpl;
import com.challenger.challengerbe.common.utils.CookieUtil;
import com.challenger.challengerbe.modules.user.repository.UserRefreshTokenRepository;
import com.challenger.challengerbe.modules.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtAuthenticationProvider authenticationProvider;
    private final UserRepository userRepository;
//    private final CustomPasswordEncoder passwordEncoder;
    private final CookieUtil cookieUtil;

    @Bean
    public CustomPasswordEncoder customPasswordEncoder() {
        return new BCryptCustomPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Authenticatoin, 토큰에 대해 인증
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(CustomPasswordEncoder passwordEncoder) throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil, userRefreshTokenRepository, userRepository, passwordEncoder, cookieUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));

        return filter;
    }


    // Authorization, 식별된 사용자에 대해 권한 부여, 인가
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService, authenticationProvider, userRefreshTokenRepository, cookieUtil);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CorsFilter corsFilter,
            CustomPasswordEncoder passwordEncoder

    ) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(corsFilter)
                .addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(passwordEncoder), UsernamePasswordAuthenticationFilter.class)
                .headers(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/users/refresh-token", "/api/v1/users/**", "/api/v1/users/login").permitAll() // 특정 경로 허용
                        .anyRequest().permitAll()
                );

        return httpSecurity.build();
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(
                List.of("http://localhost:5173", "http://localhost:5174", "http://localhost:4173"));
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}