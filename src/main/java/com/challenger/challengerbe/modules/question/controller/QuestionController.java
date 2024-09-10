package com.challenger.challengerbe.modules.question.controller;

import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;
import com.challenger.challengerbe.modules.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Long> createQuestion(@Valid @RequestBody QuestionCreateRequest request) {
        Long response = questionService.insertQuestion(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
