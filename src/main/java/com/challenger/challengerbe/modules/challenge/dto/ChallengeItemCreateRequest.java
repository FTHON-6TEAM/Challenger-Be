package com.challenger.challengerbe.modules.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeItemCreateRequest
 * author         : rhkdg
 * date           : 2024-09-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Getter
@Setter
public class ChallengeItemCreateRequest {

    @NotBlank(message = "챌린지 항목은 필수입력입니다.")
    private String title;

}
