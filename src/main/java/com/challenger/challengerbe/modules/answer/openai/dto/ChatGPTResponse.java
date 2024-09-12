package com.challenger.challengerbe.modules.answer.openai.dto;

import com.challenger.challengerbe.modules.answer.openai.domain.Choice;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : ChatGPTResponse
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
public class ChatGPTResponse {
    @JsonProperty("choices")
    private List<Choice> choices;
}
