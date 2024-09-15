package com.challenger.challengerbe.modules.question.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.common.annotation.FileUploadAction;
import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;
import com.challenger.challengerbe.modules.question.dto.QuestionDeleteRequest;
import com.challenger.challengerbe.modules.question.dto.QuestionDto;
import com.challenger.challengerbe.modules.question.dto.QuestionListDto;
import com.challenger.challengerbe.modules.question.dto.QuestionSummaryResponse;
import com.challenger.challengerbe.modules.question.dto.QuestionUpdateRequest;
import com.challenger.challengerbe.modules.question.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * packageName    : com.challenger.challengerbe.modules.question.controller
 * fileName       : QuestionController
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@Slf4j
@Tag(name = "질문 API")
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "질문 리스트 조회")
    @Parameters({
            @Parameter(name = "code", description = "키워드 (만약 전체일 경우 빈값으로 전달 주시면 되겠습니다.",required = false),
    })
    @GetMapping("/list")
    public Page<QuestionSummaryResponse> selectQuestionList(@Parameter(hidden = true) QuestionListDto searchDto) {
        Page<QuestionSummaryResponse> response = questionService.selectQuestionList(searchDto);

        return response;
    }

    @Operation(summary = "질문 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록완료"),
            @ApiResponse(responseCode = "400", description = "등록시 오류 발생")
    })
    @FileUploadAction()
    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createQuestion(@Valid @RequestPart QuestionCreateRequest request,
            @RequestPart(value = "_file",required = false) MultipartFile multipartFile
            ,@AuthInfo String userIdk) {

        try {
            QuestionDto questionDto = QuestionDto.createOf(request);
            questionDto.setUserIdx(userIdk);
            questionService.insertQuestion(questionDto);

        } catch (Exception e) {
            log.error("insert question info error : {}", e.getMessage());
            return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("등록시 오류가 발생했습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("질문 등록이 완료되었습니다."),HttpStatus.CREATED);
    }

    @Operation(summary = "질문 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제완료"),
            @ApiResponse(responseCode = "400", description = "삭제시 오류 발생")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteQuestion(@Valid @RequestBody QuestionDeleteRequest request, @AuthInfo String userIdk) {
        Long response = questionService.deleteQuestion(request, userIdk);

        return new ResponseEntity<>(CommonResponse.resAllOf("질문 삭제가 완료되었습니다.", response),HttpStatus.OK);
    }

    @Operation(summary = "질문 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정완료"),
            @ApiResponse(responseCode = "400", description = "수정시 오류 발생")
    })
    @PutMapping
    public ResponseEntity<?> updateQuestion(@Valid @RequestBody QuestionUpdateRequest request, @AuthInfo String userIdk) {
        QuestionDto questionDto = QuestionDto.updateOf(request);
        questionDto.setUserIdx(userIdk);
        questionService.updateQuestion(questionDto);

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("질문 수정이 완료되었습니다."),HttpStatus.OK);
    }

    @Operation(summary = "질문 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회완료"),
            @ApiResponse(responseCode = "400", description = "조회시 오류 발생")
    })
    @GetMapping("/view/{questionIdk}")
    public ResponseEntity<?> selectQuestion(@PathVariable("questionIdk") Long questionIdk, @AuthInfo String userIdk) {
        QuestionSummaryResponse response = questionService.selectQuestion(questionIdk, userIdk);

        return new ResponseEntity<>(CommonResponse.resAllOf("질문 단건 조회가 완료되었습니다.", response),HttpStatus.OK);
    }

}
