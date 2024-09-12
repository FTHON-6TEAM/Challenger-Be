package com.challenger.challengerbe.modules.answer.openai.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : ImageMessage
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
public class ImageMessage extends Message {
    private List<Content> content;

    public ImageMessage(String role, List<Content> content) {
        super(role);
        this.content = content;
    }
}
