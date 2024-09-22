package com.challenger.challengerbe.modules.weeklychallenge.repository;

import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeItemDto;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.repository
 * fileName       : WeeklyChallengeRepositoryCustom
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */
public interface WeeklyChallengeRepositoryCustom {

    WeeklyChallengeDto selectWeeklyChallengeDto();

    List<WeeklyChallengeItemDto> selectWeeklyChallengeItemDto(Long weeklyChallengeIdx);

    List<WeeklyChallengeDto> selectWeeklyChallengeInIdx(String userIdk);
}
