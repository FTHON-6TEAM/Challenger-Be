package com.challenger.challengerbe.modules.answer.service;

import com.challenger.challengerbe.modules.answer.domain.Answer;
import com.challenger.challengerbe.modules.answer.dto.AnswerDto;
import com.challenger.challengerbe.modules.answer.dto.AnswerResponse;
import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import com.challenger.challengerbe.modules.answer.repository.AnswerRepository;
import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.question.service.QuestionReader;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.user.service.UserService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
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
    private final QuestionReader questionReader;
    @Override
    public Long insertAnswer(AnswerDto answerDto) {
        User user = userService.selectUserByIdk(answerDto.getUserIdk());
        Question question = questionReader.selectQuestion(answerDto.getQuestionIdx());
        Answer newAnswer = Answer.builder()
                .user(user)
                .question(question)
                .content(answerDto.getContent())
                .build();
        Answer answer = answerRepository.save(newAnswer);

        return answer.getIdx();
    }

    @Async
    @Override
    public CompletableFuture<Long> insertAnswerAsync(AsyncAnswerCreateDto dto) {
        User user = userService.selectUserByIdk(dto.getUserIdk());
        Question question = questionReader.selectQuestion(dto.getQuestionIdx());
        Answer newAnswer = Answer.builder()
                .user(user)
                .question(question)
                .content(dto.getContent())
                .build();
        Answer answer = answerRepository.save(newAnswer);

        return CompletableFuture.completedFuture(answer.getIdx());
    }

    @Override
    public List<AnswerResponse> selectAnswerList(Long questionIdx) {
        List<Answer> answerList = answerRepository.findAnswerListByQuestionIdx(questionIdx);
        List<AnswerResponse> response = answerList.stream()
                .map(answer -> new AnswerResponse(
                        answer.getIdx(),
                        answer.getContent(),
                        answer.getCreateDate(),
                        answer.getModifyDate()
                ))
                .toList();

        return response;
    }
}
