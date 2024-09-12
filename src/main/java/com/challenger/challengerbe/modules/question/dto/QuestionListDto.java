package com.challenger.challengerbe.modules.question.dto;

import com.challenger.challengerbe.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto
 * fileName       : QuestionListDto
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */

@Getter
@Setter
public class QuestionListDto extends BaseDto {
    /** 키워드 **/
    private String code;
}
