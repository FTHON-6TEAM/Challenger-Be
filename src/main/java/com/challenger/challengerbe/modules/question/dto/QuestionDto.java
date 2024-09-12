package com.challenger.challengerbe.modules.question.dto;

import com.challenger.challengerbe.cms.file.dto.CmsFileDto;
import com.challenger.challengerbe.cms.file.dto.CmsFileSupport;
import com.challenger.challengerbe.modules.question.domain.Question;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.challenger.challengerbe.modules.question.dto
 * fileName       : QuestionResponse
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
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto implements CmsFileSupport<CmsFileDto> {
    private Long questionIdx;
    private String title;
    private String content;
    private List<CmsFileDto> cmsFileDtoList = new ArrayList<>();
    private String idx;
    private String code;

    public static QuestionDto createOf(QuestionCreateRequest request) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setTitle(request.getTitle());
        questionDto.setContent(request.getContent());
        questionDto.setCode(request.getPublicCode());

        return questionDto;
    }

    public static QuestionDto updateOf(QuestionUpdateRequest request) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setTitle(request.getTitle());
        questionDto.setContent(request.getContent());

        return questionDto;
    }

    @Override
    public String getParentIdx() {
        return this.idx + "";
    }

    @Override
    public String getUploadCodePath() {
        return "";
    }

    @Override
    public String getUploadCode() {
        return "upload.question";
    }

    @Override
    public CmsFileDto[] getCmsFileList() {
        return new CmsFileDto[0];
    }

    @Override
    public void addCmsFileList(CmsFileDto cmsFileDto) {
        this.cmsFileDtoList.add(cmsFileDto);
    }
}
