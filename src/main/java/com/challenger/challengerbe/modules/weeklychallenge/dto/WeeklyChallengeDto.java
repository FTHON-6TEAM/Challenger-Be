package com.challenger.challengerbe.modules.weeklychallenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeDto
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
public class WeeklyChallengeDto {

//    @Schema(description = "위클리 챌린지 참여자 키값")
//    private String idk;

    @Schema(description = "위클리 챌린지 일련번호")
    private Long weeklyChallengeIdx;

    @Schema(description = "위클리 챌린지 제목")
    private String title;

    @Schema(description = "등록일자")
    private LocalDateTime createDate;

    @Schema(description = "수정일자")
    private LocalDateTime modifyDate;

    private List<WeeklyChallengeItemDto> weeklyChallengeItemList = new ArrayList<>();

//    @Schema(description = "위클리 챌린지 방 정보")
//    private WeeklyChallengeSummaryInfoResponse weeklyChallengeSummaryInfoResponse;

    public static WeeklyChallengeUserDto createOf(WeeklyChallengeUserCreateRequest request) {
        WeeklyChallengeUserDto dto = new WeeklyChallengeUserDto();
        dto.setWeeklyChallengeIdx(request.weeklyChallengeIdx());
        return dto;
    }

    public WeeklyChallengeDto(Long weeklyChallengeIdx, String title, LocalDateTime createDate,
            LocalDateTime modifyDate) {
        this.weeklyChallengeIdx = weeklyChallengeIdx;
        this.title = title;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }


//    public static WeeklyChallengeDto createof(WeeklyChallengeCreateRequest request) {
//        WeeklyChallengeDto weeklyChallengeDto = new WeeklyChallengeDto();
//        weeklyChallengeDto.setTitle(request.getTitle());
//        weeklyChallengeDto.setWeeklyChallengeItemList(request.getItemList().stream().map(WeeklyChallengeDto::new).toList());
//
//        return weeklyChallengeDto;
//    }


}
