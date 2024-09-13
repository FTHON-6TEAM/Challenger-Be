package com.challenger.challengerbe.modules.weeklychallenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.dto
 * fileName       : WeeklyChallengeCreateRequest
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
public class WeeklyChallengeCreateRequest {
    @NotBlank
    private String title;

    // 항목 정보
    List<WeeklyChallengeItemCreateRequest> itemList = new ArrayList<>();



}
