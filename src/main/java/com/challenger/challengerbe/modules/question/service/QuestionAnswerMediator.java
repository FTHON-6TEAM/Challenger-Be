package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.answer.service.AnswerService;
import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class QuestionAnswerMediator {

    private final AnswerService answerService;
    private final OpenAiChatModel openAiChatModel;

    @Async
    public void generateAnswerForQuestion(AsyncAnswerCreateDto answerCreateDto) {
        String questionRequestToGPT =
                "나는 환경에 대해 관심이 많고 환경보호를 위해 힘을 쓰고 싶어" +
                "그래서 환경과 관련해서 질문을 하려고 해" +
                "아래 질문에 대해 적절한 답변을 해줘" +
                "답변은 50줄 이내로 작성해주고 4줄이내로 이해하기 쉽도록 설명해줘" +
                "\n"
                ;
        String questionContent = answerCreateDto.getQuestionContent();
        String result = questionRequestToGPT + questionContent;

        String openAiResponse = openAiChatModel.call(result);
        log.info(openAiResponse);
        answerCreateDto.setContent(openAiResponse);
        answerService.insertAnswerAsync(answerCreateDto);
        // 후처리
    }


}
