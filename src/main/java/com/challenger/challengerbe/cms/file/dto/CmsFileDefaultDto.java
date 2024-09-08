package com.challenger.challengerbe.cms.file.dto;

import com.challenger.challengerbe.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.cms.file.dto
 * fileName       : CmsFileDefaultDto
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : cms file search dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
@Getter
@Setter
public class CmsFileDefaultDto extends BaseDto {

    /**일련번호*/
    private Long idx = 0L;

    /**부모 pk*/
    private String parentIdx = "";

    /**업로드 코드*/
    private String uploadCode = "";

}
