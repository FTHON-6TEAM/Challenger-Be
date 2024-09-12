package com.challenger.challengerbe.modules.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto
 * fileName       : QuestionDeleteRequest
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */

@Getter
@NoArgsConstructor
public class QuestionDeleteRequest {
    @NotNull(message = "질문 idk는 필수입니다.")
    private Long questionIdk;

    @Builder
    public QuestionDeleteRequest(Long questionIdk) {
        this.questionIdk = questionIdk;
    }
}
