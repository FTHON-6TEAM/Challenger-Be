package com.challenger.challengerbe.cms.file.service;

import com.challenger.challengerbe.cms.file.dto.CmsFileSupport;

/**
 * packageName    : com.challenger.challengerbe.cms.file.service
 * fileName       : CmsFileDetailService
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : cmsFile detail service 정보
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
public interface CmsFileDetailService {

    /**
     * 파일 등록
     *
     * @param obj
     * @return
     * @throws Exception
     */
    <T extends CmsFileSupport<?>> boolean processFileCreate(T obj) throws Exception;

    /**
     * 파일 수정
     * @param obj
     * @return
     * @throws Exception
     */
    <T extends CmsFileSupport<?>>  boolean processFileUpdate(T obj) throws Exception;

    /**
     * 파일 삭제
     * @param obj
     * @return
     * @throws Exception
     */
    <T extends CmsFileSupport<?>> boolean proccessFileDelete(T obj) throws Exception;


}
