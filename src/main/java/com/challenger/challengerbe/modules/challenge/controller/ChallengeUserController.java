package com.challenger.challengerbe.modules.challenge.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.common.CommonResponse;
import com.challenger.challengerbe.common.exception.AlreadyExistException;
import com.challenger.challengerbe.common.exception.AlreadyUseException;
import com.challenger.challengerbe.modules.challenge.dto.*;
import com.challenger.challengerbe.modules.challenge.service.ChallengeApplyService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    private final ChallengeApplyService challengeApplyService;

    @Operation(summary = "챌린지 목록 조회(페이징 포함)")
    @Parameters({
            @Parameter(name = "code", description = "키워드 (만약 전체일 경우 빈값으로 전달 주시면 되겠습니다.",required = false),
            @Parameter(name = "startDate", description = "시작일자 (string 타입으로 전달 주시면 됩니다.)", example = "0000-00-00",required = false),
            @Parameter(name = "endDate", description = "마지막일자 (string 타입으로 전달 주시면 됩니다.)", example = "0000-00-00",required = false),
            @Parameter(name = "activeStatus", description = "진행상태 ", example = "ex) JOIN(모집중), KEEP(진행중), END(종료) ",required = false),
            @Parameter(name = "page", description = "페이지 번호 ", example = "1",required = false)
    })
    @GetMapping("/challenge/list")
    public Page<ChallengeSummaryResponse> selectChallengeList(@Parameter(hidden = true) ChallengeDefaultDto searchDto,
                                                              @Parameter(hidden = true) @AuthInfo String token) throws Exception {
        searchDto.setIdk(token == null ? "" : token);
        return challengeService.selectChallengePageList(searchDto);
    }

    @Operation(summary = "챌린지 상세 조회")
    @Parameter(name = "idx", description = "챌린지 일련번호")
    @GetMapping("/challenge/view")
    public ChallengeWithItemListResponse selectChallengeView(@Parameter(hidden = true) ChallengeDto challengeDto,
                                                             @Parameter(hidden = true) @AuthInfo String token) throws Exception {
        return challengeService.selectChallengeDto(challengeDto,token);
    }

    @Operation(summary = "챌린지 등록(항목 포함 일괄 처리)")
    @Parameters({
            @Parameter(name = "_file", description = "formData 에서 파일 형식, 파일 이름에 해당 , 파일명은 아무거나 선언가능 _file은 예시"),
            @Parameter(name = "_alt_file", description = " formData에서 String으로 전달,  파일의 비고명 아무거나 선언가능 대신 파일명의 name을 뒤에 그대로 붙여줘야함 _alt{파일name}")
    })
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "등록 완료"),
            @ApiResponse(responseCode = "400", description = "등록 오류 발생")
    })
    @PostMapping(value="/challenge/ins", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertChallenge(final @Valid @RequestPart ChallengeCreateRequest challengeCreateRequest,
                                             final @RequestPart(value = "_file", required = false) MultipartFile multipartFile,
                                             @AuthInfo String token) throws Exception {
        ChallengeDto challengeDto = ChallengeDto.createof(challengeCreateRequest);
        challengeDto.setIdk(token);
        challengeService.insertChallenge(challengeDto);

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("등록 되었습니다."),HttpStatus.OK);
    }

    @Operation(summary = "챌린지 수정(항목 포함 일괄 처리)")
    @Parameters({
            @Parameter(name = "_file", description = "formData 에서 파일 형식 ,파일 이름에 해당 , 파일명은 아무거나 선언가능 _file은 예시"),
            @Parameter(name = "_alt_file", description = "formData 에서 string, 파일의 비고명 아무거나 선언가능 대신 파일명의 name을 뒤에 그대로 붙여줘야함 _alt{파일name}"),
            @Parameter(name = "_modify_file", description = "file idx 값 (필수로 들어가야함), 파일의 비고명 아무거나 선언가능 대신 파일명의 name을 뒤에 그대로 붙여줘야함 _modify{파일name}",required = true),
            @Parameter(name = "_delete_file", description = "file idx 값 (이미지와 상관없이 기존에 존재하는 이미지를 지우고자 할 경우 선택), 파일의 비고명 아무거나 선언가능 대신 파일명의 name을 뒤에 그대로 붙여줘야함 _delete{파일name}")
    })
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "수정 완료"),
            @ApiResponse(responseCode = "400", description = "수정 오류 발생")
    })
    @PutMapping(value="/challenge/upd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateChallenge(final @Valid @RequestPart ChallengeUpdateRequest challengeUpdateRequest,
                                             final @RequestPart(value = "_file", required = false) MultipartFile multipartFile,
                                             @Parameter(hidden = true) @AuthInfo String token) throws Exception {

        ChallengeDto challengeDto = ChallengeDto.updateOf(challengeUpdateRequest);
        challengeDto.setIdk(token);
        challengeService.updateChallenge(challengeDto);

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

        int totCnt = challengeApplyService.selectChallengeUserTotalCountByChallengeIdx(idx);
        if(totCnt > 0) {
            throw new AlreadyUseException("이미 참여중인 참여자들이 있어 삭제가 불가능합니다.");
        }

        ChallengeDto challengeDto = new ChallengeDto();
        challengeDto.setIdx(idx);
        challengeService.deleteChallenge(challengeDto);

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("삭제 되었습니다."),HttpStatus.OK);
    }

}
