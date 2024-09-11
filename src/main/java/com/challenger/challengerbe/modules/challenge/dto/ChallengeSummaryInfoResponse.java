package com.challenger.challengerbe.modules.challenge.dto;

import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeSummaryResponse
 * author         : rhkdg
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
public record ChallengeSummaryInfoResponse(
        /**일련번호*/
        Long idx,

        /**시작일자*/
        String startDate,

        /**마지막일자*/
        String endDate,

        /***성공 조건*/
        int successCnt,

        /**제목*/
        String title,

        /**비고*/
        String remark,

        String imageLink,
        Long joinCnt,

        LocalDateTime createDate,

        LocalDateTime modifyDate
) {
}
