package com.challenger.challengerbe.modules.answer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.dto
 * fileName       : AnswerCreateRequest
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
public class AnswerCreateRequest {
    private String content;
    private String userIdk;
    private Long questionIdx;

    @Builder
    public AnswerCreateRequest(String content, String userIdk, Long questionIdx) {
        this.content = content;
        this.userIdk = userIdk;
        this.questionIdx = questionIdx;
    }
}
