package com.challenger.challengerbe.auth.security;

import com.challenger.challengerbe.module.user.domain.User;
import com.challenger.challengerbe.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public String getUserEmail(String userIdk) {
        User user = userRepository.findById(userIdk)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return user.getEmail();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new UserDetailsImpl(user);
    }
}
