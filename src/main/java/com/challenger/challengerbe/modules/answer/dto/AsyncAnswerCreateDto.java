package com.challenger.challengerbe.modules.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto
 * fileName       : AsyncAnswerCreateRequest
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsyncAnswerCreateDto {
    private String userIdk;
    private Long answerIdx;
    private Long questionIdx;
    private String questionContent;
    private String content;

    @Builder
    public AsyncAnswerCreateDto(String userIdk, Long questionIdx, String questionContent) {
        this.userIdk = userIdk;
        this.questionContent = questionContent;
        this.questionIdx = questionIdx;
    }

}
