package com.challenger.challengerbe.modules.question.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto fileName       :
 * QuestionSummaryResponse author         : jongh date           : 2024-09-12 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2024-09-12           jongh
 * 최초 생성
 */
public record QuestionSummaryResponse(
        Long idx,
        String code,
        String codeName,
        String title,
        String content,
        String username,
        String userIdk,
        @Schema(name = "fileIdx", description = "이미지 파일 일련번호")
        Long fileIdx,
        LocalDateTime createDate,
        LocalDateTime modifyDate

) {

}
