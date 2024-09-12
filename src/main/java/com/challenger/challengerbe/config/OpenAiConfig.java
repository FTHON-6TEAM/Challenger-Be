package com.challenger.challengerbe.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * packageName    : com.challenger.challengerbe.config
 * fileName       : OpenAiConfig
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */
@Configuration
public class OpenAiConfig {
    @Value("${spring.ai.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public RestTemplate template() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            request.getHeaders().set("Authorization", "Bearer " + openAiApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
