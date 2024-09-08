package com.challenger.challengerbe.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.challenger.challengerbe.config
 * fileName       : AppConfig
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : EnttiyManager 주입
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final EntityManager em;

    /**
     *query dsl
     * @return
     */
    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }
}
