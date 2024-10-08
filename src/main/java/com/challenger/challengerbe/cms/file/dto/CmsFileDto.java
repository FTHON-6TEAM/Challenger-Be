package com.challenger.challengerbe.cms.file.dto;

import com.challenger.challengerbe.cms.file.domain.CmsFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
* @packageName   : com.routdoo.dailyroutine.cms.file.dto
* @fileName      : CmsFileDto.java
* @author        : Gwang hyeok Go
* @date          : 2023.10.18
* @description   : 파일 관리 Dto
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023.10.18        ghgo       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class CmsFileDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**일련번호*/
	private Long idx;
	
	/**부모키값*/
	private String parentIdx;
	
	/**업로드코드*/
	private String uploadCode;
	
	/**실제파일명*/
	private String originalFileName;
	
	/**저장된파일명*/
	private String saveFileName;
	
	/**파일크기*/
	private String fileSize;
	
	/**확장명*/
	private String extension;
	
	/**파일태그명*/
	private String tagName;

	private String saveFilePath;

	/** 파일 alt */
	private String alt;
	
	/**등록일자*/
	private LocalDateTime createDate;
	
	/**수정일자*/
	private LocalDateTime modifyDate;

	public CmsFile toEntity(){
		return CmsFile.builder().dto(this).build();
	}

	public CmsFileDto(CmsFile entity){
		this.idx = entity.getIdx();
		this.parentIdx = entity.getParentIdx();
		this.uploadCode = entity.getUploadCode();
		this.originalFileName = entity.getOriginalFileName();
		this.saveFileName = entity.getSaveFileName();
		this.fileSize = entity.getFileSize();
		this.extension = entity.getExtension();
		this.tagName = entity.getTagName();
		this.saveFilePath = entity.getSaveFilePath();
		this.alt = entity.getAlt();
		this.createDate = entity.getCreateDate();
		this.modifyDate = entity.getModifyDate();
	}
	
}
