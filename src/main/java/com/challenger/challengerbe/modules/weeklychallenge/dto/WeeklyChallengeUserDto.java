package com.challenger.challengerbe.modules.weeklychallenge.dto;

import com.challenger.challengerbe.modules.challenge.dto.ChallengeSummaryInfoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeUserDto
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Schema(description = "위클리 챌린지 참여자 정보")
@Getter
@Setter
@NoArgsConstructor
public class WeeklyChallengeUserDto {

    @Schema(description = "위클리 챌린지 참여자 일련번호")
    private Long idx = 0L;

    @Schema(description = "위클리 챌린지 참여자 키값")
    private String idk;

    @Schema(description = "위클리 챌린지 방 일련번호")
    private Long weeklyChallengeIdx;

    @Schema(description = "등록일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifyDate;

    @Schema(description = "위클리 챌린지 방 정보")
    private WeeklyChallengeSummaryInfoResponse weeklyChallengeSummaryInfoResponse;

    public static WeeklyChallengeUserDto createOf(WeeklyChallengeUserCreateRequest request) {
        WeeklyChallengeUserDto dto = new WeeklyChallengeUserDto();
        dto.setWeeklyChallengeIdx(request.weeklyChallengeIdx());
        return dto;
    }
}
