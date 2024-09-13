package com.challenger.challengerbe.modules.challenge.statistics.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.statistics.dto.ChalendarMonthResponse;
import com.challenger.challengerbe.modules.challenge.statistics.repository.ChallengeStatisticsRepository;
import com.challenger.challengerbe.modules.challenge.statistics.service.ChallengeStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.statistics.controller
 * fileName       : ChallengeStatisticsController
 * author         : rhkdg
 * date           : 2024-09-12
 * description    : 챌린지 통계 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12        rhkdg       최초 생성
 */
@Tag(name = "챌린지 통게 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ChallengeStatisticsController {

    private final ChallengeStatisticsService challengeStatisticsService;

    @Operation(summary = "챌린지 참여율 통계 (월별)")
    @Parameter(name = "code" , description = "키워드 default는 공백입니다.")
    @GetMapping("/challenge/join/month/statistics")
    public ChalendarMonthResponse selectChallengeJoinMonthStatistics(@Parameter(hidden = true) ChallengeDefaultDto searchDto,
                                                                     @Parameter(hidden = true) @AuthInfo String token) throws Exception {

        searchDto.setIdk(token == null ? "" : token);
        return challengeStatisticsService.selectChallengeJoinStatisticsMonthList(searchDto);
    }

}
