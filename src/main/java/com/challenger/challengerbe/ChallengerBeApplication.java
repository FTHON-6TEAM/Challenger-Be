package com.challenger.challengerbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChallengerBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengerBeApplication.class, args);
    }

}
