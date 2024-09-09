package com.challenger.challengerbe.modules.challenge.domain;

import com.challenger.challengerbe.cms.publiccode.domain.PublicCode;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.domain
 * fileName       : Challenge
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 정보 엔티티
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
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_cd", nullable = false)
    private PublicCode publicCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idk", nullable = false)
    private User user;

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

    @OneToMany(mappedBy = "challenge",orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<ChallengeItem> challengeItemList = new ArrayList<>();

    public Challenge(ChallengeDto dto) {
        if(dto.getIdx() > 0){
            this.idx = idx;
        }
        this.publicCode = new PublicCode();
        publicCode.addPubCd(dto.getCode());
        this.user = new User();
        user.addIdk(dto.getIdk());
        this.startDate =  dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.successCnt = dto.getSuccessCnt();
        this.title = dto.getTitle();
        this.remark = dto.getRemark();
    }

    public void changeChallenge(ChallengeDto dto) {
        this.publicCode = new PublicCode();
        publicCode.addPubCd(dto.getCode());
        this.startDate =  dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.successCnt = dto.getSuccessCnt();
        this.title = dto.getTitle();
        this.remark = dto.getRemark();
    }

    public void addItemList(List<ChallengeItem> list) {
        challengeItemList = list;
    }
}
