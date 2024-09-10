package com.challenger.challengerbe.modules.challenge.controller;

import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.modules.challenge.dto.*;
import com.challenger.challengerbe.modules.challenge.service.ChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 목록 조회(페이징 포함)")
    @Parameters({
            @Parameter(name = "code", description = "키워드 (만약 전체일 경우 빈값으로 전달 주시면 되겠습니다.",required = false),
            @Parameter(name = "startDate", description = "시작일자 (string 타입으로 전달 주시면 됩니다.)", example = "0000-00-00",required = false),
            @Parameter(name = "endDate", description = "마지막일자 (string 타입으로 전달 주시면 됩니다.)", example = "0000-00-00",required = false),
            @Parameter(name = "activeStatus", description = "진행상태 ", example = "ex) JOIN(모집중), KEEP(진행중), END(종료) ",required = false)
    })
    @GetMapping("/challenge/list")
    public Page<ChallengeSummaryResponse> selectChallengeList(@Parameter(hidden = true) ChallengeDefaultDto searchDto) throws Exception {
        return challengeService.selectChallengePageList(searchDto);
    }

    @Operation(summary = "챌린지 상세 조회")
    @Parameter(name = "idx", description = "챌린지 일련번호")
    @GetMapping("/challenge/view")
    public ChallengeDto selectChallengeView(@Parameter(hidden = true) ChallengeDto challengeDto) throws Exception {
        return challengeService.selectChallengeDto(challengeDto);
    }

    @Operation(summary = "챌린지 등록(항목 포함 일괄 처리)")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "등록 완료"),
            @ApiResponse(responseCode = "400", description = "등록 오류 발생")
    })
    @PostMapping("/challenge/ins")
    public ResponseEntity<?> insertChallenge(final @Valid @RequestBody ChallengeCreateRequest challengeCreateRequest) throws Exception {

        try{
            ChallengeDto challengeDto = ChallengeDto.createof(challengeCreateRequest);
            challengeService.insertChallenge(challengeDto);
        }catch (Exception e) {
            logger.error("insert challenge info error : {}",e.getMessage());
            return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("등록시 오류가 발생했습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("등록 되었습니다."),HttpStatus.OK);
    }

    @Operation(summary = "챌린지 수정(항목 포함 일괄 처리)")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "수정 완료"),
            @ApiResponse(responseCode = "400", description = "수정 오류 발생")
    })
    @PutMapping("/challenge/upd")
    public ResponseEntity<?> updateChallenge(final @Valid @RequestBody ChallengeUpdateRequest challengeUpdateRequest) throws Exception {

        try{
            ChallengeDto challengeDto = ChallengeDto.updateOf(challengeUpdateRequest);
            challengeService.updateChallenge(challengeDto);
        }catch (Exception e) {
            logger.error("update challenge info error : {}",e.getMessage());
            return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("수정시 오류가 발생했습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("수정 되었습니다."),HttpStatus.OK);
    }
    
    @Operation(summary = "챌린지 삭제(전체 삭제) 단, 이미 참여자가 있다면 삭제 불가능")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "삭제 완료"),
            @ApiResponse(responseCode = "422", description = "삭제 불가"),
            @ApiResponse(responseCode = "400", description = "삭제 오류")
    })
    @DeleteMapping("/challenge/del")
    public ResponseEntity<?> deleteChallenge(@RequestParam("idx") Long idx) throws Exception {
        
        try{
            ChallengeDto challengeDto = new ChallengeDto();
            challengeDto.setIdx(idx);
            challengeService.deleteChallenge(challengeDto);
        }catch (Exception e) {
            logger.error("delete challenge info error : {}",e.getMessage());
            return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("삭제시 오류가 발생했습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("삭제 되었습니다."),HttpStatus.OK);
    }

}
