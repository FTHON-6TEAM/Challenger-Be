package com.challenger.challengerbe.modules.weeklychallenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeItemCreateRequest
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Getter
@Setter
public class WeeklyChallengeItemCreateRequest {

    @NotBlank(message = "위클리 챌린지 항목은 필수입력입니다.")
    private String title;

}
