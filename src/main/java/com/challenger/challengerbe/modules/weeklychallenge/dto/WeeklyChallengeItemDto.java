package com.challenger.challengerbe.modules.weeklychallenge.dto;

import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallengeItem;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeDtoItem
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class WeeklyChallengeItemDto {
    private Long itemIdx;
    private Long weeklyChallengeIdx;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public WeeklyChallengeItemDto(WeeklyChallengeItem item) {
        this.itemIdx = item.getIdx();
        this.weeklyChallengeIdx = item.getWeeklyChallenge().getIdx();
        this.title = item.getTitle();
        this.createDate = item.getCreateDate();
        this.modifyDate = item.getModifyDate();
    }

    public WeeklyChallengeItemDto(Long itemIdx, Long weeklyChallengeIdx, String title,
            LocalDateTime createDate, LocalDateTime modifyDate) {
        this.itemIdx = itemIdx;
        this.weeklyChallengeIdx = weeklyChallengeIdx;
        this.title = title;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public WeeklyChallengeItemDto(WeeklyChallengeItemCreateRequest request) {
        this.title = request.getTitle();
    }
}
