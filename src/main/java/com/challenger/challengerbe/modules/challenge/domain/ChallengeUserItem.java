package com.challenger.challengerbe.modules.challenge.domain;

import com.challenger.challengerbe.modules.challenge.dto.ChallengeUserItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
@Entity
@Table(name = "challenge_user_item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ChallengeUserItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_user_idx", nullable = false)
    private ChallengeUser challengeUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_item_idx", nullable = false,unique = false)
    private ChallengeItem challengeItem;
    
    @Comment("완료일자")
    @Column(length = 20)
    private String completeDate;
    
    @Comment("완료여부")
    @Column(length = 1)
    private String completeYn;
    
    @Comment("등록일자")
    @CreatedDate
    private LocalDateTime createDate;
    
    @Comment("수정일자")
    @LastModifiedDate
    private LocalDateTime modifyDate;

    public ChallengeUserItem(ChallengeUserItemDto dto) {
        if(dto.getIdx() > 0) {
            this.idx = dto.getIdx();
        }
        this.challengeUser = new ChallengeUser();
        challengeUser.addIdx(dto.getChallengeUserIdx());
        this.challengeItem = new ChallengeItem();
        challengeItem.addIdx(dto.getChallengeItemIdx());
        this.completeDate = dto.getCompleteDate();
        this.completeYn = dto.getCompleteYn();
    }

}
