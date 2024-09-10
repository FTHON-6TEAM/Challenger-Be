package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.user.domain.User;
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
 * fileName       : ChallengeUser
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
public class ChallengeUserDto {

    private Long idx;

    private String idk;

    private Long challengeIdx;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private ChallengeSummaryInfoResponse challengeSummaryInfoResponse;

    public static ChallengeUserDto createOf(ChallengeUserCreateRequest challengeUserCreateRequest) {
        ChallengeUserDto dto = new ChallengeUserDto();
        dto.setChallengeIdx(challengeUserCreateRequest.challengeIdx());
        return dto;
    }

}
