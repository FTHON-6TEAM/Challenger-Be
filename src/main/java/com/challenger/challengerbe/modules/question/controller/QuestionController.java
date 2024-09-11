package com.challenger.challengerbe.modules.question.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;
import com.challenger.challengerbe.modules.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Long> createQuestion(@Valid @RequestBody QuestionCreateRequest request, @AuthInfo String userIdk) {
        Long response = questionService.insertQuestion(request, userIdk);
        log.info("userIdk : " + userIdk);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<String> testAuthInfo(@AuthInfo String userIdk) {
        log.info("userIdk : " + userIdk);

        return ResponseEntity.ok(userIdk);
    }



}
