package com.challenger.challengerbe.modules.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeDto
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {
    
    /**일련번호*/
    private Long idx;

    /**키워드*/
    private String code;

    /**회원 idk*/
    private String idk;

    /**시작일자*/
    private String startDate;

    /**마지막일자*/
    private String endDate;

    /***성공 조건*/
    private int successCnt;

    /**제목*/
    private String title;

    /**비고*/
    private String remark;

    /**등록일자*/
    private LocalDateTime createDate;

    /**수정일자*/
    private LocalDateTime modifyDate;
    
    /**항목 목록*/
    private List<ChallengeItemDto> challengeItemList = new ArrayList<>();

}
