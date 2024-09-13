package com.challenger.challengerbe.modules.challenge.statistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.statistics.dto
 * fileName       : ChalendarMonthResponse
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
@Schema(description = "참여율 월별 통계 response")
@NoArgsConstructor
public class ChalendarMonthResponse {

    private BigDecimal month1;
    private BigDecimal month2;
    private BigDecimal month3;
    private BigDecimal month4;
    private BigDecimal month5;
    private BigDecimal month6;
    private BigDecimal month7;
    private BigDecimal month8;
    private BigDecimal month9;
    private BigDecimal month10;
    private BigDecimal month11;
    private BigDecimal month12;

    // 필요한 모든 BigDecimal을 받는 생성자 추가
    public ChalendarMonthResponse(BigDecimal month1, BigDecimal month2, BigDecimal month3, BigDecimal month4,
                   BigDecimal month5, BigDecimal month6, BigDecimal month7, BigDecimal month8,
                   BigDecimal month9, BigDecimal month10, BigDecimal month11, BigDecimal month12) {
        this.month1 = month1;
        this.month2 = month2;
        this.month3 = month3;
        this.month4 = month4;
        this.month5 = month5;
        this.month6 = month6;
        this.month7 = month7;
        this.month8 = month8;
        this.month9 = month9;
        this.month10 = month10;
        this.month11 = month11;
        this.month12 = month12;
    }

}
