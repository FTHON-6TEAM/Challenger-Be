package com.challenger.challengerbe.cms.file.repository;

import com.challenger.challengerbe.cms.file.domain.CmsFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.challenger.challengerbe.cms.file.repository
 * fileName       : CmsFileRepository
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : cmsFile CRUD repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
public interface CmsFileRepository extends JpaRepository<CmsFile,String>,CmsFileCtRepository {
}
