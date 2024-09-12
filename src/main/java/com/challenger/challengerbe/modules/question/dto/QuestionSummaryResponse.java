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
        @Schema(name = "questionIdx", description = "이미지 파일 일련번호")
        Long questionIdx,
        @Schema(name = "code", description = "키워드 pk 코드")
        String code,
        @Schema(name = "codeName", description = "키워드 명칭")
        String codeName,
        @Schema(name = "title", description = "질문 제목")
        String title,
        @Schema(name = "content", description = "질문 내용")
        String content,
        @Schema(name = "username", description = "질문자 이름")
        String username,
        @Schema(name = "userIdk", description = "질문자 유저 idk")
        String userIdk,
        @Schema(name = "fileIdx", description = "이미지 파일 일련번호")
        Long fileIdx,
        @Schema(name = "createDate", description = "생성 일자")
        LocalDateTime createDate,
        @Schema(name = "modifyDate", description = "수정 일자")
        LocalDateTime modifyDate

) {

}
