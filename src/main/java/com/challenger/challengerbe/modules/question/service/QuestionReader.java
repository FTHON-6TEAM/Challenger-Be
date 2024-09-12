package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.question.domain.Question;

/**
 * packageName    : com.challenger.challengerbe.modules.question.service
 * fileName       : QuestionReader
 * author         : jongh
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11           jongh          최초 생성
 */

public interface QuestionReader {
    Question selectQuestion(Long questionIdx);
}
