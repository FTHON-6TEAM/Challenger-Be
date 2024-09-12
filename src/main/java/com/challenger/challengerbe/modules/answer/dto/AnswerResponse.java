package com.challenger.challengerbe.modules.answer.dto;

import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.dto
 * fileName       : AnswerResponse
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */
public record AnswerResponse(
        Long answerIdx,
        String content,
        LocalDateTime createDate,
        LocalDateTime modifyDate
) {}
