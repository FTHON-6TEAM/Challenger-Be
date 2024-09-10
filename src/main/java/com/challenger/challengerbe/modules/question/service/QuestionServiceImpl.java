package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;
import com.challenger.challengerbe.modules.question.repository.QuestionRepository;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.challenger.challengerbe.modules.question.service
 * fileName       : QuestionServiceImpl
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
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Long insertQuestion(QuestionCreateRequest request) {
        User user = userService.getUserByIdk(request.getUserIdk());
        Question newQuestion = Question.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Question question = questionRepository.save(newQuestion);

        // AI answer 자동 등록 (비동기 처리)

        return question.getIdx();
    }

    @Override
    public Question getQuestionByIdx(Long questionIdx) {
        Question question = questionRepository.findById(questionIdx)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 questionIdx 입니다."));

        return question;
    }
}
