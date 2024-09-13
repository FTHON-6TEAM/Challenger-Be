package com.challenger.challengerbe.modules.challenge.dto;

import com.challenger.challengerbe.cms.file.dto.CmsFileDto;
import com.challenger.challengerbe.cms.file.dto.CmsFileSupport;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeItem;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.domain
 * fileName       : ChallengeUseritem
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
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeUserItemDto implements CmsFileSupport<CmsFileDto> {

    private Long idx = 0L;

    private Long challengeUserIdx;

    private Long challengeItemIdx;

    private String completeDate;

    private String completeYn;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    public ChallengeUserItemDto(Long idx, Long challengeUserIdx, Long challengeItemIdx, String completeDate, String completeYn, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.idx = idx;
        this.challengeUserIdx = challengeUserIdx;
        this.challengeItemIdx = challengeItemIdx;
        this.completeDate = completeDate;
        this.completeYn = completeYn;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static ChallengeUserItemDto createOf(ChallengeUserItemCreateRequest challengeUserItemCreateRequest) {
        ChallengeUserItemDto dto = new ChallengeUserItemDto();
        dto.setChallengeUserIdx(challengeUserItemCreateRequest.challengeUserIdx());
        dto.setChallengeItemIdx(challengeUserItemCreateRequest.challengeItemIdx());
        dto.setCompleteDate(challengeUserItemCreateRequest.completeDate());
        dto.setCompleteYn(challengeUserItemCreateRequest.completeYn());
        return dto;
    }

    private List<CmsFileDto> cmsFileList = new ArrayList<>();


    @Override
    public String getParentIdx() {
        return this.idx+"";
    }

    @Override
    public String getUploadCodePath() {
        return null;
    }

    @Override
    public String getUploadCode() {
        return "upload.challenge.apply";
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
