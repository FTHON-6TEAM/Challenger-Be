package com.challenger.challengerbe.modules.answer.service;

import com.challenger.challengerbe.modules.answer.dto.AnswerCreateRequest;
import java.util.concurrent.CompletableFuture;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.service fileName       :
 * AnswerService author         : jongh date           : 2024-09-10 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2024-09-10           jongh
 * 최초 생성
 */
public interface AnswerService {

    Long insertAnswer(AnswerCreateRequest request);

    CompletableFuture<Long> insertAnswerAsync(AnswerCreateRequest request);

}
