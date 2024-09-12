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
public class WeeklyChallengeDefaultDto extends BaseDto {
    /**키워드*/
    private String code;

    private String userIdk;

    /**시작일자*/
    private String startDate;

    /**마지막일자*/
    private String endDate;

    /**진행 상태값*/
    private String activeStatus;
}
