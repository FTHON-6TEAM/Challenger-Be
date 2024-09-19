package com.challenger.challengerbe.modules.weeklychallenge.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.service.WeeklyChallengeApplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.controller
 * fileName       : WeeklyChallengeUserController
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Tag(name = "위클리 챌린지 참여 컨트롤러")
@RestController
@RequestMapping("/api/v1/weekly")
@RequiredArgsConstructor
public class WeeklyChallengeApplyController {
    /*
    위클리 챌린지 참여하기
     */

    private final WeeklyChallengeApplyService weeklyChallengeApplyService;

    @Operation(summary = "위클리 챌린지 참여")
    @PostMapping
    public ResponseEntity<?> applyWeeklyChallenge(@RequestBody WeeklyChallengeDefaultDto searchDto, @AuthInfo String userIdk) {
        searchDto.setUserIdk(userIdk);
        weeklyChallengeApplyService.applyChallenge(searchDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
