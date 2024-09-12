package com.challenger.challengerbe.modules.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.dto
 * fileName       : AnswerDto
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
public class AnswerDto {
    private Long answerIdx;
    private Long questionIdx;
    private String content;
    private String userIdk;

    public static AnswerDto createOf(AnswerCreateRequest request) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setQuestionIdx(request.getQuestionIdx());
        answerDto.setContent(request.getContent());

        return answerDto;
    }
}
