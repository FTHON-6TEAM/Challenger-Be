package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.dto.QuestionCreateRequest;
import com.challenger.challengerbe.modules.question.dto.QuestionDeleteRequest;
import com.challenger.challengerbe.modules.question.dto.QuestionDto;
import com.challenger.challengerbe.modules.question.dto.QuestionGetRequest;
import com.challenger.challengerbe.modules.question.dto.QuestionListDto;
import com.challenger.challengerbe.modules.question.dto.QuestionSummaryResponse;
import com.challenger.challengerbe.modules.question.dto.QuestionUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Long insertQuestion(QuestionDto questionDto) throws Exception;

    QuestionSummaryResponse selectQuestion(Long questionIdk, String userIdk);

    Long deleteQuestion(QuestionDeleteRequest request, String userIdk);

    void updateQuestion(QuestionDto questionDto);

    Page<QuestionSummaryResponse> selectQuestionList(QuestionListDto searchDto);

}
