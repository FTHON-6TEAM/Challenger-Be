package com.challenger.challengerbe.modules.answer.openai.dto;

import com.challenger.challengerbe.modules.answer.openai.domain.ImageContent;
import com.challenger.challengerbe.modules.answer.openai.domain.ImageMessage;
import com.challenger.challengerbe.modules.answer.openai.domain.ImageUrl;
import com.challenger.challengerbe.modules.answer.openai.domain.Message;
import com.challenger.challengerbe.modules.answer.openai.domain.TextContent;
import com.challenger.challengerbe.modules.answer.openai.domain.TextMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : ChatGPTRequest
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
@Builder
public class ChatGPTRequest {
    @JsonProperty("model")
    private String model;
    @JsonProperty("messages")
    private List<Message> messages;
    @JsonProperty("max_tokens")
    private int maxTokens;

    public static ChatGPTRequest createImageRequest(String model, int maxTokens, String role, String requestText, String imageUrl) {
        TextContent textContent = new TextContent("text", requestText);
        ImageContent imageContent = new ImageContent("image_url", new ImageUrl(imageUrl));
        Message message = new ImageMessage(role, List.of(textContent, imageContent));
        return createChatGPTRequest(model, maxTokens, Collections.singletonList(message));
    }

    public static ChatGPTRequest createTextRequest(String model, int maxTokens, String role, String requestText) {
        Message message = new TextMessage(role, requestText);
        return createChatGPTRequest(model, maxTokens, Collections.singletonList(message));
    }

    private static ChatGPTRequest createChatGPTRequest(String model, int maxTokens, List<Message> messages) {
        return ChatGPTRequest.builder()
                .model(model)
                .maxTokens(maxTokens)
                .messages(messages)
                .build();
    }
}
