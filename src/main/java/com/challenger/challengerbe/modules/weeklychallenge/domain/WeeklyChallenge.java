package com.challenger.challengerbe.modules.weeklychallenge.domain;

import com.challenger.challengerbe.cms.publiccode.domain.PublicCode;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeItem;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.domain
 * fileName       : WeeklyChallenge
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WeeklyChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pub_cd", nullable = false)
//    private PublicCode publicCode;

    @Comment("시작일자")
    @Column(length = 20)
    private String startDate;

    @Comment("마지막일자")
    @Column(length = 20)
    private String endDate;

    @Comment("성공 횟수")
    private int successCnt;

    @Comment("제목")
    private String title;

    @Lob
    @Comment("비고")
    private String remark;

    @Comment("등록일자")
    @CreatedDate
    private LocalDateTime createDate;

    @Comment("수정일자")
    @LastModifiedDate
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "weeklyChallenge",orphanRemoval = true, cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    private List<WeeklyChallengeItem> weeklyChallengeItemList = new ArrayList<>();

    public WeeklyChallenge(String title) {
        this.title = title;
        this.startDate = String.valueOf(LocalDateTime.now());
        this.endDate = String.valueOf(LocalDateTime.now().plusWeeks(1));
    }

    public void addIdx(Long idx) {
        this.idx = idx;
    }

    public void addItemList(List<WeeklyChallengeItem> list) {
        weeklyChallengeItemList = list;
    }
}
