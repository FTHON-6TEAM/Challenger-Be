package com.challenger.challengerbe.modules.challenge.controller;

import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.challenge.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.controller
 * fileName       : ChallengeUserController
 * author         : rhkdg
 * date           : 2024-09-10
 * description    : 챌린지 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
@Tag(name = "챌린지 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ChallengeUserController {

    private ChallengeService challengeService;

    @Operation(summary = "챌린지 목록 조회(페이징 포함)")
    @Parameters({
            @Parameter(name = "code", description = "키워드"),
            @Parameter(name = "startDate", description = "시작일자"),
            @Parameter(name = "endDate", description = "마지막일자")
    })
    @GetMapping("/challenge/list")
    public Page<ChallengeDto> selectChallengeList(@Parameter(hidden = true) ChallengeDefaultDto searchDto) throws Exception {
        return challengeService.selectChallengePageList(searchDto);
    }

    @Operation(summary = "챌린지 상세 조회")
    @Parameter(name = "idx", description = "챌린지 일련번호")
    @GetMapping("/challenge/view")
    public ChallengeDto selectChallengeView(@Parameter(hidden = true) ChallengeDto challengeDto) throws Exception {
        return challengeService.selectChallengeDto(challengeDto);
    }

}
