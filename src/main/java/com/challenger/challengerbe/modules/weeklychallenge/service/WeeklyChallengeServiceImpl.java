package com.challenger.challengerbe.modules.weeklychallenge.service;

import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeSummaryResponse;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.service
 * fileName       : WeeklyChallengeServiceImpl
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
public class WeeklyChallengeServiceImpl implements WeeklyChallengeService {

    private final WeeklyChallengeRepository weeklyChallengeRepository;


    @Override
    public Page<WeeklyChallengeSummaryResponse> selectWeeklyChallengePageList(
            WeeklyChallengeDefaultDto searchDto) {
        return null;
    }
}
