package com.challenger.challengerbe.modules.challenge.controller;

import com.challenger.challengerbe.auth.login.AuthInfo;
import com.challenger.challengerbe.common.CommonResponse;
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

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.controller
 * fileName       : ChallengeApplyController
 * author         : rhkdg
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
@Tag(name = "챌린지 참여 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ChallengeApplyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final ChallengeApplyService challengeApplyService;


    @Operation(summary = "챌린지 참여 목록 조회(페이징 포함)")
    @Parameters({
            @Parameter(name = "startDate", description = "시작일자 (string 타입으로 전달 주시면 됩니다.)", example = "0000-00-00",required = false),
            @Parameter(name = "endDate", description = "마지막일자 (string 타입으로 전달 주시면 됩니다.)", example = "0000-00-00",required = false)
    })
    @GetMapping("/challenge/apply/list")
    public Page<ChallengeUserDto> selectChallengeUserList(@Parameter(hidden = true)ChallengeDefaultDto searchDto) throws Exception {
        return challengeApplyService.selectChallengeUserPageList(searchDto);
    }

    @Operation(summary = "챌린지 참여 목록 조회(페이징 포함)")
    @Parameter(name = "idx" , description = "참여중인 챌린지 idx")
    @GetMapping("/challenge/apply/view")
    public ChallengeUserDto selectChallengeUserView(@Parameter(hidden = true) ChallengeUserDto dto) throws Exception {
        return challengeApplyService.selectChallengeUser(dto);
    }

    @Operation(summary = "챌린지 참여 등록(항목 포함 일괄 처리)")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "등록 완료"),
            @ApiResponse(responseCode = "400", description = "등록 오류")
    })
    @PostMapping("/challenge/apply/ins")
    public ResponseEntity<?> insertChallenge(final @Valid @RequestBody ChallengeUserCreateRequest challengeUserCreateRequest,
                                             @Parameter(hidden = true) @AuthInfo String token) throws Exception {

        try{
            ChallengeUserDto challengeUserDto = ChallengeUserDto.createOf(challengeUserCreateRequest);
            challengeUserDto.setIdk(token);
            challengeApplyService.insertChallengeApply(challengeUserDto);
        }catch (Exception e) {
            logger.error("insert challenge apply info error : {}",e.getMessage());
            return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("등록시 오류가 발생했습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("등록 되었습니다."),HttpStatus.OK);
    }

    @Operation(summary = "챌린지 참여 삭제(항목 포함 일괄 처리)")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "삭제 완료"),
            @ApiResponse(responseCode = "400", description = "삭제 오류")
    })
    @DeleteMapping("/challenge/apply/del")
    public ResponseEntity<?> deleteChallenge(@RequestParam("idx") Long idx) throws Exception {

       ChallengeUserDto dto = new ChallengeUserDto();
       dto.setIdx(idx);
       challengeApplyService.deleteChallengeApply(dto);

       return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("삭제되었습니다."),HttpStatus.OK);
    }

    @Operation(summary = "챌린지 참여자가 챌린지 항목에 대한 성공 처리")
    @Parameters({
            @Parameter(name = "_file", description = "formData 에서 파일 형식, 파일 이름에 해당 , 파일명은 아무거나 선언가능 _file은 예시"),
            @Parameter(name = "_alt_file", description = " formData에서 String으로 전달,  파일의 비고명 아무거나 선언가능 대신 파일명의 name을 뒤에 그대로 붙여줘야함 _alt{파일name}")
    })
    @PostMapping(value = "/challenge/apply/item/ins",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertChallengeApplyItem(
            final @Valid @RequestPart ChallengeUserItemCreateRequest challengeUserItemCreateRequest) throws Exception {
        
        ChallengeUserItemDto dto = ChallengeUserItemDto.createOf(challengeUserItemCreateRequest);
        challengeApplyService.insertChallengeApplyItem(dto);

        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("성공하셨습니다."),HttpStatus.OK);
    }

    @Operation(summary = "챌린지 참여자가 챌린지 항목에 대한 실패 처리")
    @DeleteMapping(value = "/challenge/apply/item/del")
    public ResponseEntity<?> deleteChallengeApplyItem(@RequestParam("idx") Long idx) throws Exception {
        ChallengeUserItemDto dto = new ChallengeUserItemDto();
        dto.setIdx(idx);
        challengeApplyService.deleteChallengeApplyItem(dto);
        return new ResponseEntity<>(CommonResponse.resOnlyMessageOf("취소하셨습니다."),HttpStatus.OK);
    }
    
}
