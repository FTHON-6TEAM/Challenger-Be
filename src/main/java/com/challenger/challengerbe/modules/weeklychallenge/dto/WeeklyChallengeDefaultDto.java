package com.challenger.challengerbe.modules.weeklychallenge.dto;

import com.challenger.challengerbe.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeDefaultDto
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Getter
@Setter
public class WeeklyChallengeDefaultDto {
    /**키워드*/
    private String code;

    /** 위클리 챌린지 id */
    private Long weeklyChallengeIdx;

    private String userIdk;
}
