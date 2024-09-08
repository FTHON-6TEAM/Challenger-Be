package com.challenger.challengerbe.cms.file.dto;

/**
 * packageName    : com.challenger.challengerbe.cms.file.dto
 * fileName       : CmsFileSupport
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : 파일 생성 support interface
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
public interface CmsFileSupport <T>{
    /**
     * 파일 부모 정보
     * @return
     */
    String getParentIdx();

    /**
     * 파일 업로드 코드 경로
     * @return
     */
    String getUploadCodePath();

    /**
     * 파일 업로드 코드
     * @return
     */
    String getUploadCode();

    /**
     * 파일 목록
     * @return
     */
    T[] getCmsFileList();

    /**
     * 파일 set
     * @param t
     */
    void addCmsFileList(T t);

}
