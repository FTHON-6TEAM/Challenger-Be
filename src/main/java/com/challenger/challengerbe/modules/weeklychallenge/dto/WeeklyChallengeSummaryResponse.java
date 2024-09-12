package com.challenger.challengerbe.modules.weeklychallenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeSummaryResponse
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Schema(name = "ChallengeSummaryResponse", description = "위클리 챌린지 모집 정보")
public record WeeklyChallengeSummaryResponse(
        @Schema(name = "idx",description = "챌린지 일련번호")
        Long idx,

        @Schema(name = "code", description = "키워드 PK 코드")
        String code,

        @Schema(name="codeName" ,description = "키워드 명칭")
        String codeName,

        @Schema(name = "successCnt",description = "성공 조건")
        int successCnt,

        @Schema(name = "title",description = "제목")
        String title,

        @Schema(name = "remark",description = "비고")
        String remark,

        @Schema(name = "joinCnt",description = "참여자 수")
        Long joinCnt,

        @Schema(name = "createDate", description = "등록 일자")
        LocalDateTime createDate,

        @Schema(name = "modifyDate", description = "수정 일자")
        LocalDateTime modifyDate

) {
}
