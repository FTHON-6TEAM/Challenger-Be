package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.cms.file.dto.CmsFileDto;
import com.challenger.challengerbe.cms.file.dto.CmsFileSupport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.dto
 * fileName       : ChallengeDto
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 Dto
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto implements CmsFileSupport<CmsFileDto> {
    
    /**일련번호*/
    private Long idx = 0L;

    /**키워드*/
    private String code;

    /**회원 idk*/
    private String idk;

    /**시작일자*/
    private String startDate;

    /**마지막일자*/
    private String endDate;

    /***성공 조건*/
    private int successCnt;

    /**제목*/
    private String title;

    /**비고*/
    private String remark;

    /**등록일자*/
    private LocalDateTime createDate;

    /**수정일자*/
    private LocalDateTime modifyDate;
    
    /**항목 목록*/
    private List<ChallengeItemDto> challengeItemList = new ArrayList<>();

    private List<CmsFileDto> cmsFileList = new ArrayList<>();

    public static ChallengeDto createof(ChallengeCreateRequest challengeCreateRequest) {
        ChallengeDto challengeDto =new ChallengeDto();
        challengeDto.setCode(challengeCreateRequest.getCode());
        challengeDto.setStartDate(challengeCreateRequest.getStartDate());
        challengeDto.setEndDate(challengeCreateRequest.getEndDate());
        challengeDto.setSuccessCnt(challengeCreateRequest.getSuccessCnt());
        challengeDto.setTitle(challengeCreateRequest.getTitle());
        challengeDto.setRemark(challengeCreateRequest.getRemark());
        challengeDto.setChallengeItemList(challengeCreateRequest.getItemList().stream().map(ChallengeItemDto::new).toList());
        return challengeDto;
    }

    public static ChallengeDto updateOf(ChallengeUpdateRequest challengeUpdateRequest) {
        ChallengeDto challengeDto =new ChallengeDto();
        challengeDto.setIdx(challengeUpdateRequest.getIdx());
        challengeDto.setCode(challengeUpdateRequest.getCode());
        challengeDto.setStartDate(challengeUpdateRequest.getStartDate());
        challengeDto.setEndDate(challengeUpdateRequest.getEndDate());
        challengeDto.setSuccessCnt(challengeUpdateRequest.getSuccessCnt());
        challengeDto.setTitle(challengeUpdateRequest.getTitle());
        challengeDto.setRemark(challengeUpdateRequest.getRemark());
        challengeDto.setChallengeItemList(challengeUpdateRequest.getItemList().stream().map(ChallengeItemDto::new).toList());
        return challengeDto;
    }

    @Override
    public String getParentIdx() {
        return this.idx+"";
    }

    @Override
    public String getUploadCodePath() {
        return "";
    }

    @Override
    public String getUploadCode() {
        return "upload.challenge";
    }

    @Override
    public CmsFileDto[] getCmsFileList() {
        return new CmsFileDto[0];
    }

    @Override
    public void addCmsFileList(CmsFileDto cmsFileDto) {
        this.cmsFileList.add(cmsFileDto);
    }
}
