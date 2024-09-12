package com.challenger.challengerbe.modules.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeItemSummaryResponse
 * author         : rhkdg
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13        rhkdg       최초 생성
 */
public record ChallengeUserItemSummaryResponse (
        
        @Schema(description = "챌린지 참여 항목 일련번호")
        Long idx,
        
        @Schema(description = "완료일자")
        String completeDate,

        @Schema(description = "완료여부")
        String completeYn

){
}
