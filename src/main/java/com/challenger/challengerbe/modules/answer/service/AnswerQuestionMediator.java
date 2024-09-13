package com.challenger.challengerbe.modules.answer.service;

import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import com.challenger.challengerbe.modules.answer.openai.dto.ChatGPTResponse;
import com.challenger.challengerbe.modules.answer.openai.service.AiCallService;
import com.challenger.challengerbe.modules.question.repository.QuestionRepository;
import java.io.IOException;
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
    private final AiCallService aiCallService;
    private final QuestionRepository questionRepository;


    @Async
    public void generateAnswerForQuestion(AsyncAnswerCreateDto answerCreateDto) throws IOException {
        // file idx 를 가져와야 한다.
        // query 작성

//        Long fileIdx = questionRepository.selectFileIdxByQuestion(answerCreateDto.getQuestionIdx());
//        String preFileUrl = "http://115.85.182.23:32468/cms/file/image/link/";
//        String fileUrl = preFileUrl + "?idx=" +fileIdx;
        String fileUrl = "https://ojsfile.ohmynews.com/CT_T_IMG/2022/0715/IE003021157_LT.jpg";
//        String fileUrl = "";
        log.info("file URL : " + fileUrl);

        ChatGPTResponse chatGPTResponse = aiCallService.requestImageAnalysisWithUrl(fileUrl, answerCreateDto.getQuestionContent());

        // openai 가 이미지와 질문을 분석한 결과 값
        String imageAnalysisResult = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        log.info("imageAnalysisResult" + imageAnalysisResult);

        String userText = answerCreateDto.getQuestionContent();

        // 유저가 질문한 내용은 중요도가 높으므로 이미지 분석시 1회, 최종 결과 반환시 1회로 총 2번 사용
        Message userMessage = new UserMessage(userText);
//        String systemText = """
//                I care about the environment and want to do my part to protect it.
//                That's why I'm going to ask you some questions about the environment.
//                Please give me a good answer to the question below.
//                Please write your answer in 50 lines or less and explain it in 4 lines or less so that it is easy to understand.
//                And please write in Korean, considering Korean grammar.
//                """;
        String systemText = """
                The following image has been analyzed: %s
                Additionally, here is the user's question: %s
                Please provide an answer that addresses the user's question while considering the content of the image. 
                Ensure that the answer is clear, concise, and understandable within 50 lines or less, and summarize it in 4 lines or less.
                Please write in Korean, with proper grammar and easy-to-understand language.
                """.formatted(imageAnalysisResult, userText);

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
