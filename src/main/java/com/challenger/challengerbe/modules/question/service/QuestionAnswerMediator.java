package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.answer.dto.AnswerCreateRequest;
import com.challenger.challengerbe.modules.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.challenger.challengerbe.modules.question.service
 * fileName       : QuestionAnswerMediator
 * author         : jongh
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11           jongh          최초 생성
 */

@Component
@RequiredArgsConstructor
public class QuestionAnswerMediator {

    private final AnswerService answerService;
//    private final OpenAiChatModel openAiChatModel;

    @Async
    public void generateAnswerForQuestion(Long questoinIdx, String userIdk) {
//        String aiResponse = openAiChatModel.generateResponse(questionContent);
        String aiResponse = "ai response";
        AnswerCreateRequest answerCreateRequest = new AnswerCreateRequest(aiResponse, userIdk, questoinIdx);
        answerService.insertAnswerAsync(answerCreateRequest);
    }


}
