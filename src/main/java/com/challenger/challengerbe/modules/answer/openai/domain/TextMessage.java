package com.challenger.challengerbe.modules.answer.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : TextMessage
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
@AllArgsConstructor
@NoArgsConstructor
public class TextMessage extends Message {
    private String content;

    public TextMessage(String role, String content) {
        super(role);
        this.content = content;
    }
}
