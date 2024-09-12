package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeItem;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeItemDto
 * author         : rhkdg
 * date           : 2024-09-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class ChallengeItemDto {

    private Long idx = 0L;


    private Long challengeIdx;


    private String title;


    private LocalDateTime createDate;


    private LocalDateTime modifyDate;


    private ChallengeUserItemSummaryResponse challengeUserItemSummaryResponse;

    public ChallengeItemDto(ChallengeItem entity) {
        this.idx = entity.getIdx();
        this.challengeIdx = entity.getChallenge().getIdx();
        this.title = entity.getTitle();
        this.createDate = entity.getCreateDate();
        this.modifyDate = entity.getModifyDate();
    }

    public ChallengeItemDto(Long idx, Long challengeIdx, String title, LocalDateTime createDate, LocalDateTime modifyDate, ChallengeUserItemSummaryResponse challengeUserItemSummaryResponse) {
        this.idx = idx;
        this.challengeIdx = challengeIdx;
        this.title = title;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.challengeUserItemSummaryResponse = challengeUserItemSummaryResponse;
    }

    public ChallengeItemDto(ChallengeItemCreateRequest request){
        this.title = request.getTitle();
    }
}
