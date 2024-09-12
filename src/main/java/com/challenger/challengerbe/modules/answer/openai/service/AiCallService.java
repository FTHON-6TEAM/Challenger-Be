package com.challenger.challengerbe.modules.answer.openai.service;

import com.challenger.challengerbe.modules.answer.openai.dto.ChatGPTRequest;
import com.challenger.challengerbe.modules.answer.openai.dto.ChatGPTResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : AiCallService
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */

@Service
@RequiredArgsConstructor
public class AiCallService {
    @Value("${spring.ai.openai.model}")
    private String apiModel;

    @Value("${spring.ai.openai.url}")
    private String apiUrl;

    private final RestTemplate template;

    public ChatGPTResponse requestTextAnalysis(String requestText) {
        ChatGPTRequest request = ChatGPTRequest.createTextRequest(apiModel, 500, "user", requestText);
        return template.postForObject(apiUrl, request, ChatGPTResponse.class);
    }

    public ChatGPTResponse requestImageAnalysis(MultipartFile image, String requestText) throws IOException {
        String base64Image = Base64.encodeBase64String(image.getBytes());
        String imageUrl = "data:image/jpeg;base64," + base64Image;
        ChatGPTRequest request = ChatGPTRequest.createImageRequest(apiModel, 500, "user", requestText, imageUrl);
        return template.postForObject(apiUrl, request, ChatGPTResponse.class);
    }

    public ChatGPTResponse requestImageAnalysisWithUrl(String imageUrl, String requestText) throws IOException {
        ChatGPTRequest request = ChatGPTRequest.createImageRequest(apiModel, 500, "user", requestText, imageUrl);
        return template.postForObject(apiUrl, request, ChatGPTResponse.class);
    }
}
