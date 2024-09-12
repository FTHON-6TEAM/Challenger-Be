package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.cms.file.dto.CmsFileDto;
import com.challenger.challengerbe.cms.file.dto.CmsFileSupport;
import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
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
import java.util.ArrayList;
import java.util.List;

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
@Schema(description = "챌린지 참여자 정보")
@Getter
@Setter
@NoArgsConstructor
public class ChallengeUserDto  {

    @Schema(description = "챌린지 참여자 일련번호")
    private Long idx;

    @Schema(description = "챌린지 참여자 키값")
    private String idk;

    @Schema(description = "챌린지 방 일련번호")
    private Long challengeIdx;

    @Schema(description = "등록일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifyDate;

    @Schema(description = "챌린지 방 정보")
    private ChallengeSummaryInfoResponse challengeSummaryInfoResponse;

    public ChallengeUserDto(Long idx, String idk, Long challengeIdx, LocalDateTime createDate, LocalDateTime modifyDate, ChallengeSummaryInfoResponse challengeSummaryInfoResponse) {
        this.idx = idx;
        this.idk = idk;
        this.challengeIdx = challengeIdx;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.challengeSummaryInfoResponse = challengeSummaryInfoResponse;
    }

    public static ChallengeUserDto createOf(ChallengeUserCreateRequest challengeUserCreateRequest) {
        ChallengeUserDto dto = new ChallengeUserDto();
        dto.setChallengeIdx(challengeUserCreateRequest.challengeIdx());
        return dto;
    }


}
