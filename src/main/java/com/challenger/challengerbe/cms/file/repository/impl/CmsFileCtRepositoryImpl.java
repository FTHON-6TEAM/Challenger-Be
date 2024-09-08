package com.challenger.challengerbe.cms.file.repository.impl;

import com.challenger.challengerbe.cms.file.domain.CmsFile;
import com.challenger.challengerbe.cms.file.domain.QCmsFile;
import com.challenger.challengerbe.cms.file.dto.CmsFileDefaultDto;
import com.challenger.challengerbe.cms.file.dto.CmsFileDto;
import com.challenger.challengerbe.cms.file.repository.CmsFileCtRepository;
import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.cms.file.repository.impl
 * fileName       : CmsFileCtRepositoryImpl
 * author         : rhkdg
 * date           : 2024-09-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
@Repository
public class CmsFileCtRepositoryImpl extends BaseAbstractRepositoryImpl implements CmsFileCtRepository {
    protected CmsFileCtRepositoryImpl(EntityManager entityManager, JPAQueryFactory jpaQuery) {
        super(entityManager, jpaQuery);
    }

    @Override
    public List<CmsFileDto> selectCmsFileList(CmsFileDefaultDto searchDto) throws Exception {
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        List<CmsFile> list = jpaQuery.selectFrom(qCmsFile).fetch();

        return list.stream().map(CmsFileDto::new).toList();
    }

    @Override
    public CmsFileDto selectCmsFile(CmsFileDto dto) throws Exception {

        QCmsFile qCmsFile = QCmsFile.cmsFile;

        CmsFile cmsFile = jpaQuery.selectFrom(qCmsFile)
                .where(qCmsFile.idx.eq(dto.getIdx())).fetchFirst();

        return cmsFile == null ? null : new CmsFileDto(cmsFile);
    }

    @Override
    public Long selectCmsFileTotalCount(CmsFileDefaultDto searchDto) throws Exception {
        QCmsFile qCmsFile = QCmsFile.cmsFile;
        return jpaQuery.select(qCmsFile.count())
                .from(qCmsFile)
                .fetchOne();
    }

    @Override
    public boolean insertCmsFile(CmsFileDto dto) throws Exception {
        QCmsFile qCmsFile = QCmsFile.cmsFile;
        return entityManager.createNativeQuery("insert into cms_file (parent_idx, upload_code, original_file_name,save_file_name,file_size, extension,tag_name,save_file_path,alt,create_date,modify_date" +
                        ") values (?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1,dto.getParentIdx())
                .setParameter(2,dto.getUploadCode())
                .setParameter(3,dto.getOriginalFileName())
                .setParameter(4,dto.getSaveFileName())
                .setParameter(5,dto.getFileSize())
                .setParameter(6,dto.getExtension())
                .setParameter(7,dto.getTagName())
                .setParameter(8,dto.getSaveFilePath())
                .setParameter(9,dto.getAlt())
                .setParameter(10, LocalDateTime.now())
                .setParameter(11,LocalDateTime.now())
                .executeUpdate() > 0;
    }

    @Override
    public boolean updateCmsFile(CmsFileDto dto) throws Exception {
        QCmsFile qCmsFile = QCmsFile.cmsFile;
        return jpaQuery.update(qCmsFile)
                .set(qCmsFile.uploadCode,dto.getUploadCode())
                .set(qCmsFile.originalFileName,dto.getOriginalFileName())
                .set(qCmsFile.saveFileName,dto.getSaveFileName())
                .set(qCmsFile.fileSize,dto.getFileSize())
                .set(qCmsFile.extension,dto.getExtension())
                .set(qCmsFile.tagName,dto.getTagName())
                .set(qCmsFile.alt,dto.getAlt())
                .where(qCmsFile.idx.eq(dto.getIdx())).execute() > 0;
    }

    @Override
    public boolean deleteCmsFile(CmsFileDto dto) throws Exception {
        QCmsFile qCmsFile = QCmsFile.cmsFile;
        return jpaQuery.delete(qCmsFile)
                .where(qCmsFile.idx.eq(dto.getIdx())).execute() > 0;
    }
}
