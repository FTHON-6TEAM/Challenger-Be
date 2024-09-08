package com.challenger.challengerbe.module.user.repository;

import com.challenger.challengerbe.module.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

}
