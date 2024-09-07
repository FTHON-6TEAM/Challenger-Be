package com.challenger.challengerbe.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String idk;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    public enum Role {
        ROLE_ADMIN("admin"), ROLE_USER("user");
        private String description;
        Role(String description) {
            this.description = description;
        }
    }

    public User() {

    }

    @Builder
    public User(String username, String email, String password, String idk, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.idk = idk;
        this.role = role;
    }
}
