package com.challenger.challengerbe.modules.user.domain;

import com.challenger.challengerbe.auth.security.CustomPasswordEncoder;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
    private String idk;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Embedded
    private UserPassword password;

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
    public User(String idk, String username, String email, CustomPasswordEncoder passwordEncoder, Role role) {
        this.idk = idk;
        this.username = username;
        this.email = email;
        this.password = new UserPassword(idk, passwordEncoder);
        this.role = role;
    }

    public void addIdk(String idk) {
        this.idk = idk;
    }
}
