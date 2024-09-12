package com.challenger.challengerbe.modules.answer.service;

import com.challenger.challengerbe.modules.answer.dto.AnswerDto;
import com.challenger.challengerbe.modules.answer.dto.AnswerResponse;
import com.challenger.challengerbe.modules.answer.dto.AsyncAnswerCreateDto;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.service fileName       :
 * AnswerService author         : jongh date           : 2024-09-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2024-09-10           jongh
 * 최초 생성
 */
public interface AnswerService {

    Long insertAnswer(AnswerDto answerDto);

    CompletableFuture<Long> insertAnswerAsync(AsyncAnswerCreateDto createDto);

    List<AnswerResponse> selectAnswerList(Long questionIdx);
}
