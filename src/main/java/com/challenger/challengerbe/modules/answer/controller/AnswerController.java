package com.challenger.challengerbe.modules.answer.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.modules.answer.dto.AnswerCreateRequest;
import com.challenger.challengerbe.modules.answer.dto.AnswerDto;
import com.challenger.challengerbe.modules.answer.dto.AnswerResponse;
import com.challenger.challengerbe.modules.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "답변 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록완료"),
            @ApiResponse(responseCode = "400", description = "등록시 오류 발생")
    })
    @PostMapping
    public ResponseEntity<?> insertAnswer(@Valid @RequestBody AnswerCreateRequest request, @AuthInfo String userIdk) {
        AnswerDto answerDto = AnswerDto.createOf(request);
        answerDto.setUserIdk(userIdk);
        Long response = answerService.insertAnswer(answerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.resAllOf("질문 생성이 완료되었습니다.", response));
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

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.resAllOf("답변 조회가 완료되었습니다.", response));
    }
}
