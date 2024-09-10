package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeDefaultDto
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 검색용 dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Getter
@Setter
public class ChallengeDefaultDto extends BaseDto {

    /**키워드*/
    private String code;

    private String idk;

    /**시작일자*/
    private String startDate;

    /**마지막일자*/
    private String endDate;

    /**진행 상태값*/
    private String activeStatus;

}
