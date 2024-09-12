package com.challenger.challengerbe.modules.weeklychallenge.dto;

import jakarta.validation.constraints.NotNull;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto fileName       :
 * WeeklyChallengeUserCreateRequest author         : jongh date           : 2024-09-13 description
 *  : =========================================================== DATE              AUTHOR
 *   NOTE ----------------------------------------------------------- 2024-09-13           jongh
 * 최초 생성
 */
public record WeeklyChallengeUserCreateRequest(
        @NotNull(message = "위클리 챌린지 일련번호는 필수값입니다.")
        Long weeklyChallengeIdx
) {

}
