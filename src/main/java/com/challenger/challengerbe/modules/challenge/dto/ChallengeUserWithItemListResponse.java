package com.challenger.challengerbe.modules.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeUserWithItemListResponse
 * author         : rhkdg
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13        rhkdg       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeUserWithItemListResponse {

    private ChallengeUserDto challengeUser;

    private List<List<ChallengeItemDto>> itemList = new ArrayList<>();

}
