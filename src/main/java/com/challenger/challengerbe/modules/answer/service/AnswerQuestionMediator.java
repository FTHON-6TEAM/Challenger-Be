package com.challenger.challengerbe.modules.answer.service;

import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
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
public class AnswerQuestionMediator {

    private final AnswerService answerService;
    private final OpenAiChatModel openAiChatModel;

    @Async
    public void generateAnswerForQuestion(AsyncAnswerCreateDto answerCreateDto) {
        String userText = answerCreateDto.getQuestionContent();
        Message userMessage = new UserMessage(userText);
        String systemText = """
                I care about the environment and want to do my part to protect it.
                That's why I'm going to ask you some questions about the environment.
                Please give me a good answer to the question below.
                Please write your answer in 50 lines or less and explain it in 4 lines or less so that it is easy to understand.
                And please write in Korean, considering Korean grammar.
                """;
        String name = "오늘의 챌린지 AI";
        String voice = "informative";
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(
                Map.of("name", name, "voice", voice));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        ChatResponse response = openAiChatModel.call(prompt);
        String openAiResponse = response.getResult().getOutput().getContent();

        log.info(openAiResponse);
        answerCreateDto.setContent(openAiResponse);
        answerService.insertAnswerAsync(answerCreateDto);
        // 후처리
    }


}
