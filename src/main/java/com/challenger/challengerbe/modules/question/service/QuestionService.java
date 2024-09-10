package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;

/**
 * packageName    : com.challenger.challengerbe.modules.question.service
 * fileName       : QuestionService
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */
public interface QuestionService {

    Long insertQuestion(QuestionCreateRequest request);

    Question getQuestionByIdx(Long questionIdx);
}
