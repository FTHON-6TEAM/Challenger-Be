package com.challenger.challengerbe.modules.weeklychallenge.service;

import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.repository.UserRepository;
import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallenge;
import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallengeUser;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeUserRepository;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.service
 * fileName       : WeeklyChallengeApplyServiceImpl
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Service
@RequiredArgsConstructor
public class WeeklyChallengeApplyServiceImpl implements WeeklyChallengeApplyService {

    private final WeeklyChallengeUserRepository weeklyChallengeUserRepository;
    private final WeeklyChallengeRepository weeklyChallengeRepository;
    private final UserRepository userRepository;

    @Override
    public void applyChallenge(WeeklyChallengeDefaultDto searchDto) {
        Long weeklyChallengeIdx = searchDto.getWeeklyChallengeIdx();
        String userIdk = searchDto.getUserIdk();
        WeeklyChallenge weeklyChallenge = weeklyChallengeRepository.findById(weeklyChallengeIdx).orElseThrow();
        User user = userRepository.findById(userIdk).orElseThrow();
        WeeklyChallengeUser weeklyChallengeUser = WeeklyChallengeUser.builder()
                .user(user)
                .weeklyChallenge(weeklyChallenge)
                .build();
        weeklyChallengeUserRepository.save(weeklyChallengeUser);
    }
}
