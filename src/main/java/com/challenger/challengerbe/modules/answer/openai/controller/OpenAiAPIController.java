package com.challenger.challengerbe.modules.answer.openai.controller;

import com.challenger.challengerbe.modules.answer.openai.service.AiCallService;
import com.challenger.challengerbe.modules.answer.openai.dto.ChatGPTResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.openai
 * fileName       : OpenAiAPIController
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OpenAiAPIController {
    private final AiCallService aiCallService;

    @PostMapping("/image")
    public String imageAnalysis(@RequestParam MultipartFile image, @RequestParam String requestText)
            throws IOException {
        ChatGPTResponse response = aiCallService.requestImageAnalysis(image, requestText);
        return response.getChoices().get(0).getMessage().getContent();
    }

    @PostMapping("/text")
    public String textAnalysis(@RequestParam String requestText) {
        ChatGPTResponse response = aiCallService.requestTextAnalysis(requestText);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
