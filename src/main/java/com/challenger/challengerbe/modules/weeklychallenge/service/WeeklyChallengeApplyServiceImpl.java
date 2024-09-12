package com.challenger.challengerbe.modules.weeklychallenge.service;

import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeSummaryResponse;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeUserDto;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    private final WeeklyChallengeApplyRepository weeklyChallengeApplyRepository;


    @Override
    public Page<WeeklyChallengeUserDto> selectChallengeUserPageList(
            WeeklyChallengeDefaultDto searchDto) {
        return null;
    }
}
