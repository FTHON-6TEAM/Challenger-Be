package com.challenger.challengerbe.modules.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeResponse
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
@Schema(description = "챌린지 방 정보와 항목 정보 response")
public class ChallengeWithItemListResponse {

    /**일련번호*/
    @Schema(description = "챌린지 방 일련번호")
    private Long idx = 0L;

    /**키워드*/
    @Schema(description = "챌린지 방 일련번호")
    private String code;
    
    /**시작일자*/
    @Schema(description = "챌린지 시작 일자")
    private String startDate;

    /**마지막일자*/
    @Schema(description = "챌린지 마지막 일자")
    private String endDate;

    @Schema(description = "챌린지 항목 성공조건")
    /***성공 조건*/
    private int successCnt;

    @Schema(description = "챌린지 제목")
    /**제목*/
    private String title;

    @Schema(description = "챌린지 비고(설명글)")
    /**비고*/
    private String remark;

    @Schema(description = "챌린지 키워드 명칭")
    /**키워드 이름*/
    private String codeName;

    @Schema(description = "챌린지 방장 이름")
    /**챌린지 방장 */
    private String username;

    @Schema(description = "챌린지 등록일자")
    /**등록일자*/
    private LocalDateTime createDate;

    @Schema(description = "챌린지 수정일자")
    /**수정일자*/
    private LocalDateTime modifyDate;

    @Schema(description = "챌린지 참여자가 방에 참여한 여부")
    private boolean isJoin = false;

    /**항목 목록*/
    @Schema(description = "챌린지 항목 목록")
    private List<ChallengeItemDto> challengeItemList = new ArrayList<>();


    public ChallengeWithItemListResponse(Long idx, String code, String startDate, String endDate, int successCnt, String title, String remark, String codeName, String username, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.idx = idx;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.successCnt = successCnt;
        this.title = title;
        this.remark = remark;
        this.codeName = codeName;
        this.username = username;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
