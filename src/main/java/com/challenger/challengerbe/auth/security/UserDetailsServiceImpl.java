package com.challenger.challengerbe.auth.security;

import com.challenger.challengerbe.modules.user.controller.LoginRequest;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.dto.UserCreateRequest;
import com.challenger.challengerbe.modules.user.repository.UserRepository;
import com.challenger.challengerbe.modules.user.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
//        Optional<User> user = userRepository.findByEmail(email);
        Optional<User> user = userRepository.findByEmail(email);
//
//        // Authentication 객체에서 LoginRequest 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginRequest loginRequest = (LoginRequest) authentication.getDetails(); // details에서 가져옴
//
//        // 만약 email 로 검색시 없을 경우 회원가입 진행 후 로그인 진행
//        if (user.isEmpty()) {
//            User newUser = User.builder().idk(loginRequest.getIdk())
//                    .username(loginRequest.getUsername()).email(
//                            loginRequest.getEmail()).build();
//            User getUser = userRepository.save(newUser);
//            return new UserDetailsImpl(getUser);
//        }

        return new UserDetailsImpl(user.get());
    }
}
