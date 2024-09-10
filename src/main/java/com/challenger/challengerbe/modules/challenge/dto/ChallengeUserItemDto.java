package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeItem;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.domain
 * fileName       : ChallengeUseritem
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
@AllArgsConstructor
public class ChallengeUserItemDto {

    private Long idx;

    private Long challengeUserIdx;

    private Long challengeItemIdx;

    private String completeDate;

    private String completeYn;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

}
