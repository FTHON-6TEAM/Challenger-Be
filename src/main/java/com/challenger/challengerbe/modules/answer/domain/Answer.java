package com.challenger.challengerbe.modules.answer.domain;

import com.challenger.challengerbe.modules.question.domain.Question;
import com.challenger.challengerbe.modules.user.domain.User;
import jakarta.persistence.Column;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * packageName    : com.challenger.challengerbe.modules.question
 * fileName       : Answer
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idk", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_idx", nullable = false)
    private Question question;

    @Column(name = "content", length = 3000)
    private String content;

    @Comment("등록일자")
    @CreatedDate
    private LocalDateTime createDate;

    @Comment("수정일자")
    @LastModifiedDate
    private LocalDateTime modifyDate;

    private boolean isAi = false;

    @Builder
    public Answer(User user, Question question, String content) {
        this.user = user;
        this.question = question;
        this.content = content;
    }

    public void setIsAi() {
        this.isAi = true;
    }
}
