package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.answer.dto.AnswerDto;
import com.challenger.challengerbe.modules.answer.service.AnswerService;
import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import lombok.RequiredArgsConstructor;
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
    public void generateAnswerForQuestion(AsyncAnswerCreateDto answerCreateDto) {
//        String aiResponse = openAiChatModel.generateResponse(questionContent);
        String aiResponse = "ai response";
        answerCreateDto.setContent(aiResponse); // 예시
        answerService.insertAnswerAsync(answerCreateDto);
        // 후처리
    }


}
