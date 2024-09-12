package com.challenger.challengerbe.modules.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto
 * fileName       : QuestionUpdateRequest
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
public class QuestionUpdateRequest {
    @NotNull(message = "질문 idk는 필수입니다.")
    private Long questionIdk;

    @NotNull(message = "제목은 필수입니다.")
    private String title;

    @NotNull(message = "내용은 필수입니다.")
    private String content;

    @Builder
    public QuestionUpdateRequest(Long questionIdk, String title, String content) {
        this.questionIdk = questionIdk;
        this.title = title;
        this.content = content;
    }
}
