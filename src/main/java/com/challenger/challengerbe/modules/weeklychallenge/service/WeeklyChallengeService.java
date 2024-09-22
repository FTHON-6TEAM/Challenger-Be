package com.challenger.challengerbe.modules.weeklychallenge.service;

import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeItemDto;
import java.util.List;

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

    WeeklyChallengeDto selectWeeklyChallengeDto();

    void insertWeeklyChallenge(WeeklyChallengeDto challengeDto);

    List<WeeklyChallengeDto> selectMyWeeklyChallenge(String userIdk);
}
