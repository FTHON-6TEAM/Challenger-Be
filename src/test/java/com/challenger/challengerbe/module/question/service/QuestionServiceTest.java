package com.challenger.challengerbe.module.question.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;
import com.challenger.challengerbe.modules.question.service.QuestionService;
import com.challenger.challengerbe.modules.user.dto.UserCreateRequest;
import com.challenger.challengerbe.modules.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.challenger.challengerbe.module.question.service
 * fileName       : QuestionServiceTest
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@SpringBootTest
@Transactional
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @DisplayName("질문을 생성한다")
    @Test
    void insertQuestion() {
        // given
        String email = "jonghuncu@gmail.com";
        String password = "password";
        String username = "name";
        String role = "admin";
        UserCreateRequest request = new UserCreateRequest("idk", email, username);
        String userIdk = userService.insertUser(request);

        QuestionCreateRequest questionCreateRequest = new QuestionCreateRequest("title", "content");

        // when
        Long questionIdx =  questionService.insertQuestion(questionCreateRequest, userIdk);
        Question question = questionService.getQuestionByIdx(questionIdx);

        // then
        assertThat(question.getContent()).isEqualTo("content");
    }

}
