package com.challenger.challengerbe.modules.challenge.dto;

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
@Schema(description = "챌린지 참여자에게 제공하는 챌린지 정보")
public record ChallengeSummaryInfoResponse(
        /**일련번호*/
        @Schema(name = "idx",description = "챌린지 일련번호")
        /**일련번호*/
        Long idx,

        @Schema( name = "startDate" ,description = "챌린지 시작일자")
        /**시작일자*/
        String startDate,

        @Schema(name = "endDate",description = "챌린지 마지막일자")
        /**마지막일자*/
        String endDate,

        @Schema(name = "successCnt",description = "성공 조건")
        /***성공 조건*/
        int successCnt,

        @Schema(name = "title",description = "제목")
        /**제목*/
        String title,

        @Schema(name = "remark",description = "비고")
        /**비고*/
        String remark,

        @Schema(name = "fileIdx", description = "이미지 파일 일련번호")
        Long fileIdx,

        @Schema(name = "joinCnt",description = "참여자 수")
        Long joinCnt,

        @Schema(name = "createDate", description = "등록 일자")
        LocalDateTime createDate,

        @Schema(name = "modifyDate", description = "수정 일자")
        LocalDateTime modifyDate
) {
}
