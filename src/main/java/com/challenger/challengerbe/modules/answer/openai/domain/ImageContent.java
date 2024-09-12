package com.challenger.challengerbe.modules.answer.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : ImageContent
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
public class ImageContent extends Content {
    private ImageUrl image_url;

    public ImageContent(String type, ImageUrl image_url) {
        super(type);
        this.image_url = image_url;
    }
}
