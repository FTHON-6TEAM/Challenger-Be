package com.challenger.challengerbe.modules.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeUserItemCreateRequest
 * author         : rhkdg
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11        rhkdg       최초 생성
 */
@Schema(description = "챌린지 참여자의 챌린지 항목 응답 request")
public record ChallengeUserItemCreateRequest(

        @Schema(description = "챌린지 참여자 일련번호")
        @NotNull(message = "챌린지 참여자 일련번호는 필수입력입니다.")
        Long challengeUserIdx,

        @Schema(description = "챌린지 항목 일련번호")
        @NotNull(message = "챌린지 항목 일련번호는 필수입력입니다.")
        Long challengeItemIdx,

        @Schema(description = "완료일자" ,example = "0000-00-00")
        @NotBlank(message = "챌린지 항목 완료일자는 필수입력입니다.")
        String completeDate,

        @Schema(description = "완료여부(Y/N)" ,example = "Y")
        @NotBlank(message = "챌린지 항목 완료여부는 필수입력입니다.")
        String completeYn
) {
}
