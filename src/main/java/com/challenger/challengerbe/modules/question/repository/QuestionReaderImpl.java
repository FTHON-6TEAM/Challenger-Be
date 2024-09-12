package com.challenger.challengerbe.modules.question.repository;

import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.repository.QuestionRepository;
import com.challenger.challengerbe.modules.question.service.QuestionReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.challenger.challengerbe.modules.question.service
 * fileName       : QuestionReaderImpl
 * author         : jongh
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11           jongh          최초 생성
 */

@Component
@RequiredArgsConstructor
public class QuestionReaderImpl implements QuestionReader {

    private final QuestionRepository questionRepository;


    @Override
    public Question selectQuestion(Long questionIdx) {
        return questionRepository.findById(questionIdx)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문입니다."));
    }
}
