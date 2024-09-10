package com.challenger.challengerbe.modules.challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeDto
 * author         : rhkdg
 * date           : 2024-09-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Getter
@Setter
public class ChallengeUpdateRequest {

    @NotNull(message = "일련번호는 필수입력입니다.")
    private Long idx;

    @NotBlank(message = "키워드는 필수입력입니다.")
    private String code;
    
    @NotBlank(message = "시작일자는 필수입력입니다.")
    private String startDate;

    @NotBlank(message = "마지막일자느 필수입력입니다.")
    private String endDate;

    @NotNull
    private int successCnt;

    @NotBlank(message = "제목은 필수입력입니다.")
    private String title;

    @NotNull
    private String remark;
    
    /**항목 정보*/
    List<ChallengeItemCreateRequest> itemList = new ArrayList<>();

}
