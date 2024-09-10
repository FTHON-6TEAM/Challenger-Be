package com.challenger.challengerbe.modules.answer.service;

import com.challenger.challengerbe.modules.answer.domain.Answer;
import com.challenger.challengerbe.modules.answer.dto.AnswerCreateRequest;
import com.challenger.challengerbe.modules.answer.repository.AnswerRepository;
import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.service.QuestionService;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.service
 * fileName       : AnswerServiceImpl
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@Component
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserService userService;
    private final QuestionService questionService;

    @Override
    public Long insertAnswer(AnswerCreateRequest request) {
        User user = userService.getUserByIdk(request.getUserIdk());
        Question question = questionService.getQuestionByIdx(request.getQuestionIdx());
        Answer newAnswer = Answer.builder()
                .user(user)
                .question(question)
                .content(request.getContent())
                .build();
        Answer answer = answerRepository.save(newAnswer);

        return answer.getIdx();
    }
}
