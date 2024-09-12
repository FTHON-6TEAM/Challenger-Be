package com.challenger.challengerbe.config;

import com.challenger.challengerbe.auth.login.AuthRedirectArgumentResolver;
import java.util.List;

import com.challenger.challengerbe.cms.file.CmsFileThreadLocalInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * packageName    : com.challenger.challengerbe.config
 * fileName       : WebConfig
 * author         : jongh
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11           jongh          최초 생성
 */

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    /**jwt 토큰 사용 handler*/
    private final AuthRedirectArgumentResolver authRedirectArgumentResolver;

    /**파일 처리 handler*/
    private final CmsFileThreadLocalInterceptor cmsFileThreadLocalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cmsFileThreadLocalInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authRedirectArgumentResolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000","http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type", "Bearer", "credentials")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
