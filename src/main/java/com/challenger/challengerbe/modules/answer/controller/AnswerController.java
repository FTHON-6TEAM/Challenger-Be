package com.challenger.challengerbe.modules.answer.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.modules.answer.dto.AnswerCreateRequest;
import com.challenger.challengerbe.modules.answer.dto.AnswerDto;
import com.challenger.challengerbe.modules.answer.dto.AnswerResponse;
import com.challenger.challengerbe.modules.answer.openai.dto.ChatGPTResponse;
//import com.challenger.challengerbe.modules.answer.openai.service.AiCallService;
import com.challenger.challengerbe.modules.answer.openai.service.AiCallService;
import com.challenger.challengerbe.modules.answer.service.AnswerService;
import com.challenger.challengerbe.modules.question.repository.QuestionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.controller
 * fileName       : AnswerController
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final OpenAiChatModel openAiChatModel;
    private final QuestionRepository questionRepository;
    private final AiCallService aiCallService;

    @Operation(summary = "답변 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록완료"),
            @ApiResponse(responseCode = "400", description = "등록시 오류 발생")
    })
    @PostMapping
    public ResponseEntity<Long> insertAnswer(@Valid @RequestBody AnswerCreateRequest request, @AuthInfo String userIdk) {
        AnswerDto answerDto = AnswerDto.createOf(request);
        answerDto.setUserIdk(userIdk);
        Long response = answerService.insertAnswer(answerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "답변 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회완료"),
            @ApiResponse(responseCode = "400", description = "조회시 오류 발생")
    })
    @Parameter(name = "questionIdx", description = "질문의 idx")
    @GetMapping("/{questionIdx}")
    public ResponseEntity<?> selectAnswerList(@PathVariable("questionIdx") Long questionIdx) {
        List<AnswerResponse> response = answerService.selectAnswerList(questionIdx);

        return new ResponseEntity<>(CommonResponse.resAllOf("답변 조회가 완료되었습니다.", response),HttpStatus.CREATED);
    }

    @PostMapping(value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Map<String, String> chat(@RequestBody String message) {
        Map<String, String> responses = new HashMap<>();

        String openAiResponse = openAiChatModel.call(message);
        responses.put("openai(chatGPT) 응답", openAiResponse);

        return responses;
    }
//
//    @GetMapping("/test")
//    public Long test() {
//        Long response = questionRepository.selectFileIdxByQuestion(14L);
//        return response;
//    }

//    @GetMapping("/image/test")
//    public String testImgae() throws IOException {
//        String fileUrl = "http://115.85.182.23:32468/cms/file/image/link?idx=1";
//        String userText = "이 상자는 플라스틱 박스입니다. 환경보호를 위해 어떻게 하면 좋을까요 ?";
//        ChatGPTResponse chatGPTResponse = aiCallService.requestImageAnalysisWithUrl(fileUrl, userText);
//
//        // openai 가 이미지와 질문을 분석한 결과 값
//        String imageAnalysisResult = chatGPTResponse.getChoices().get(0).getMessage().getContent();
//
//        return imageAnalysisResult;
//    }

}
