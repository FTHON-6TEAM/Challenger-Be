package com.challenger.challengerbe.modules.weeklychallenge.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeCreateRequest;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDto;
import com.challenger.challengerbe.modules.weeklychallenge.service.WeeklyChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/v1/weekly-challenge")
@RequiredArgsConstructor
public class WeeklyChallengeController {

    /**
     * 현재 진행중인 위클리 챌린지 정보
     * 현재 진행중인 위클리 챌린지 아이템 정보
     * 위클리 챌린지 최종 성공률
     * 위클리 챌린지 성공 등록
     * 위클리 챌린지 참여 정보
     */

    private final WeeklyChallengeService weeklyChallengeService;

    @Operation(summary = "위클리 챌린지 상세 조회")
    @Parameter(name = "searchDto", description = "위클리 챌린지 일련번호")
    @GetMapping("/view")
    public ResponseEntity<?> selectWeeklyChallenge (@AuthInfo String userIdk) {
        WeeklyChallengeDto response = weeklyChallengeService.selectWeeklyChallengeDto();

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.resAllOf("위클리 챌린지 상세 조회가 완료되었습니다.", response));
    }

//    @Operation(summary = "위클리 챌린지 전체 성공률 조회")
//    @Parameter(name = "success", description = "위클리 챌린지 일련번호")
//    @GetMapping("/success")
//    public ResponseEntity<?> successWeeklyRate(@AuthInfo String userIdk) {
//        return null;
//    }

    @Operation(summary = "내가 참여한 위클리 챌린지 조회")
    @GetMapping("/me")
    public ResponseEntity<?> selectMyWeeklyChallenge(@AuthInfo String userIdk) {
        List<WeeklyChallengeDto> response = weeklyChallengeService.selectMyWeeklyChallenge(userIdk);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.resAllOf("내가 참여한 위클리 챌린지 조회가 완료되었습니다.", response));
    }

    @Operation(summary = "위클리 챌린지 생성")
    @PostMapping
    public ResponseEntity<?> insertWeeklyChallenge(@RequestBody WeeklyChallengeCreateRequest request) {
        WeeklyChallengeDto challengeDto = WeeklyChallengeDto.createof(request);
        weeklyChallengeService.insertWeeklyChallenge(challengeDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
