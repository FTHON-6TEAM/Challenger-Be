package com.challenger.challengerbe.modules.answer.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : TextContent
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
public class TextContent extends Content {
    private String text;

    public TextContent(String type, String text) {
        super(type);
        this.text = text;
    }
}
