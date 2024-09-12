package com.challenger.challengerbe.modules.answer.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : Choice
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
@AllArgsConstructor
public class Choice {
    private int index;
    private TextMessage message;
}
