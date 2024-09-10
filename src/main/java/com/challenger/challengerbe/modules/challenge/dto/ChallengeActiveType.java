package com.challenger.challengerbe.modules.challenge.dto;

import lombok.Getter;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeActiveType
 * author         : rhkdg
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
@Getter
public enum ChallengeActiveType {

    JOIN("모집중"),
    KEEP("진행중"),
    END("종료");

    private String display = "";

    ChallengeActiveType(String display) {
        this.display = display;
    }
}
