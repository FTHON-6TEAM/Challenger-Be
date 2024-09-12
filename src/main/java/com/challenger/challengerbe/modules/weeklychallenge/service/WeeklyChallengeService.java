package com.challenger.challengerbe.modules.weeklychallenge.service;

import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeSummaryResponse;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.service
 * fileName       : WeeklyChallengeService
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */
public interface WeeklyChallengeService {

    Page<WeeklyChallengeSummaryResponse> selectWeeklyChallengePageList(
            WeeklyChallengeDefaultDto searchDto);
}
