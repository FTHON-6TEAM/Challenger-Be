package com.challenger.challengerbe.modules.challenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.domain
 * fileName       : ChallengeItem
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 항목 정보
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ChallengeItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_idx" ,nullable = false)
    private Challenge challenge;

    @Comment("제목")
    private String title;

    @Comment("등록일자")
    @CreatedDate
    private LocalDateTime createDate;

    @Comment("수정일자")
    @LastModifiedDate
    private LocalDateTime modifyDate;

    public void addChallenge(Challenge challenge){
        this.challenge = challenge;
    }
    public void addIdx(Long idx) {
        this.idx = idx;
    }
    public void addTitle(String title) {
        this.title = title;
    }

}
