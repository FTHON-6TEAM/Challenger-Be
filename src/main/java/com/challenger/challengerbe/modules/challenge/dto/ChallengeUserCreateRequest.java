package com.challenger.challengerbe.modules.challenge.dto;

import jakarta.validation.constraints.NotNull;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeUserCreateRequest
 * author         : rhkdg
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11        rhkdg       최초 생성
 */
public record ChallengeUserCreateRequest(

        @NotNull(message = "챌린지 일련번호는 필수입력입니다.")
        Long challengeIdx

) {
}
