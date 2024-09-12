package com.challenger.challengerbe.modules.question.repository;

import com.challenger.challengerbe.modules.question.dto.QuestionListDto;
import com.challenger.challengerbe.modules.question.dto.QuestionSummaryResponse;
import org.springframework.data.domain.Page;

/**
 * packageName    : com.challenger.challengerbe.modules.question.repository
 * fileName       : QuestionRepositoryCustom
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */
public interface QuestionRepositoryCustom {

    Page<QuestionSummaryResponse> selectQuestionList(QuestionListDto searchDto);

    QuestionSummaryResponse selectQuestionDto(Long questionIdk);
}
