package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(name = "챌린지 모집 정보")
public record ChallengeSummaryResponse(
        
        @Schema(name = "챌린지 일련번호")
        /**일련번호*/
        Long idx,

        @Schema(name = "키워드 PK 코드")
        /**키워드*/
        String code,

        @Schema(name = "키워드 명칭")
        String codeName,

        @Schema(name = "챌린지 방장이름")
        String username,

        @Schema(name = "챌린지 방장 idk")
        /**회원 idk*/
        String idk,

        @Schema(name = "챌린지 시작일자")
        /**시작일자*/
         String startDate,

        @Schema(name = "챌린지 마지막일자")
        /**마지막일자*/
         String endDate,

        @Schema(name = "성공 조건")
        /***성공 조건*/
         int successCnt,

        @Schema(name = "제목")
        /**제목*/
         String title,

        @Schema(name = "비고")
        /**비고*/
         String remark,

        @Schema(name = "이미지 파일 일련번호")
         Long fileIdx,

        @Schema(name = "참여자 수")
         Long joinCnt,

        @Schema(name = "등록 일자")
         LocalDateTime createDate,

        @Schema(name = "수정 일자")
         LocalDateTime modifyDate
) {
}
