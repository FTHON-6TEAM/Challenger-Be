package com.challenger.challengerbe.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    private Long idk;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
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
    public User(Long idk, String username, String email, String password, Role role) {
        this.idk = idk;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
