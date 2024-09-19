package com.challenger.challengerbe.modules.weeklychallenge.domain;

import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeItemDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.domain fileName       :
 * WeeklyChallengeItem author         : jongh date           : 2024-09-13 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2024-09-13           jongh
 * 최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WeeklyChallengeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_challenge_idx", nullable = false)
    private WeeklyChallenge weeklyChallenge;

    @Comment("제목")
    private String title;

    @Comment("등록일자")
    @CreatedDate
    private LocalDateTime createDate;

    @Comment("수정일자")
    @LastModifiedDate
    private LocalDateTime modifyDate;

    public void addChallenge(WeeklyChallenge weeklyChallenge){
        this.weeklyChallenge = weeklyChallenge;
    }
    public void addIdx(Long idx) {
        this.idx = idx;
    }
    public void addTitle(String title) {
        this.title = title;
    }

    public WeeklyChallengeItem(WeeklyChallengeItemDto dto) {
        this.title = dto.getTitle();
    }

}
