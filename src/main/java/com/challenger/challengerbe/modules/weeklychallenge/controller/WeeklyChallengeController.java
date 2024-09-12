package com.challenger.challengerbe.modules.weeklychallenge.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeSummaryResponse;
import com.challenger.challengerbe.modules.weeklychallenge.service.WeeklyChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.controller
 * fileName       : WeeklyChallengeController
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */
@Tag(name = "위클리 컨트롤러")
@RestController
@RequiredArgsConstructor
public class WeeklyChallengeController {

    private final WeeklyChallengeService weeklyChallengeService;

    @Operation(summary = "위클리 챌린지 목록 조회(페이징 포함)")
    @Parameters({
            @Parameter(name = "code", description = "키워드 (만약 전체일 경우 빈값으로 전달 주시면 되겠습니다.",required = false),
            @Parameter(name = "activeStatus", description = "진행상태 ", example = "ex) JOIN(모집중), KEEP(진행중), END(종료) ",required = false),
            @Parameter(name = "page", description = "페이지 번호 ", example = "1",required = false)
    })
    @GetMapping("/weekly-challenge/list")
    public Page<WeeklyChallengeSummaryResponse> selectWeeklyChallengeList(
            @Parameter WeeklyChallengeDefaultDto searchDto, @AuthInfo String userIdk) {
        searchDto.setUserIdk(userIdk);
        return weeklyChallengeService.selectWeeklyChallengePageList(searchDto);
    }
}
