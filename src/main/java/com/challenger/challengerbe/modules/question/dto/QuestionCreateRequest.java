package com.challenger.challengerbe.modules.question.dto;

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
    private String title;
    private String content;

    @Builder
    public QuestionCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
