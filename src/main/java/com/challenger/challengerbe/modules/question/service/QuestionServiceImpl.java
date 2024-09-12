package com.challenger.challengerbe.modules.question.service;

import com.challenger.challengerbe.cms.file.service.CmsFileService;
import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import com.challenger.challengerbe.modules.question.dto.QuestionDeleteRequest;
import com.challenger.challengerbe.modules.question.dto.QuestionDto;
import com.challenger.challengerbe.modules.question.dto.QuestionListDto;
import com.challenger.challengerbe.modules.question.dto.QuestionSummaryResponse;
import com.challenger.challengerbe.modules.question.repository.QuestionRepository;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final QuestionAnswerMediator mediator;
    private final CmsFileService cmsFileService;

    @Override
    @Transactional
    public Long insertQuestion(QuestionDto questionDto) throws Exception {
        User user = userService.selectUserByIdk(questionDto.getUserIdx());
        Question newQuestion = Question.builder()
                .user(user)
                .title(questionDto.getTitle())
                .content(questionDto.getContent())
                .dto(questionDto)
                .build();
        Question question = questionRepository.save(newQuestion);
        cmsFileService.processFileCreate(questionDto);

        AsyncAnswerCreateDto request = AsyncAnswerCreateDto.builder()
                .questionIdx(question.getIdx())
                .userIdk(questionDto.getUserIdx())
                .build();

        // AI answer 자동 등록 (@Async 비동기 처리)
        // 비동기 등록
        mediator.generateAnswerForQuestion(request);

        return question.getIdx();
    }

    @Override
    public QuestionSummaryResponse selectQuestion(Long questionIdk, String userIdk) {
        return questionRepository.selectQuestionDto(questionIdk);
    }

    @Override
    @Transactional
    public Long deleteQuestion(QuestionDeleteRequest request, String userIdk) {
        Question question = questionRepository.findById(request.getQuestionIdk())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 질문입니다."));
        if (!(question.getUser().getIdk().equals(userIdk))) {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }
        questionRepository.deleteById(request.getQuestionIdk());

        return request.getQuestionIdk();
    }

    @Override
    @Transactional
    public void updateQuestion(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getQuestionIdx())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 질문입니다."));
        if (!(question.getUser().getIdk().equals(questionDto.getUserIdx()))) {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }
        question.updateTitleAndContent(questionDto);
    }

    @Override
    public Page<QuestionSummaryResponse> selectQuestionList(QuestionListDto searchDto) {
        return questionRepository.selectQuestionList(searchDto);
    }
}
