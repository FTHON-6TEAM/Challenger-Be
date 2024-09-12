package com.challenger.challengerbe.modules.answer.openai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : Message
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
public abstract class Message {
    private String role;
}