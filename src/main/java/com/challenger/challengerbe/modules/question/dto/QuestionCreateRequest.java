package com.challenger.challengerbe.modules.question.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto
 * fileName       : QuestionCreateRequest
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@Getter
@NoArgsConstructor
public class QuestionCreateRequest {
    @NotNull(message = "제목은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "내용은 필수 입력값입니다.")
    private String content;

    @NotNull(message = "키워드는 필수 입니다.")
    private String publicCode;

    @Builder
    public QuestionCreateRequest(String title, String content, String publicCode) {
        this.title = title;
        this.content = content;
        this.publicCode = publicCode;
    }
}
