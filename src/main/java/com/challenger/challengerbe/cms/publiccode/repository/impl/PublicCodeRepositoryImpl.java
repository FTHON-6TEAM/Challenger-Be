package com.challenger.challengerbe.cms.publiccode.repository.impl;

import com.challenger.challengerbe.cms.publiccode.domain.PublicCode;
import com.challenger.challengerbe.cms.publiccode.domain.QPublicCode;
import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDefaultDto;
import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDto;
import com.challenger.challengerbe.cms.publiccode.repository.PublicCodeRepository;
import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.cms.publiccode.repository.impl
 * fileName       : PublicCodeRepositoryImpl
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : 공통 코드 repository impl
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
@Repository
public class PublicCodeRepositoryImpl extends BaseAbstractRepositoryImpl implements PublicCodeRepository {


    protected PublicCodeRepositoryImpl(EntityManager entityManager, JPAQueryFactory jpaQuery) {
        super(entityManager, jpaQuery);
    }

    @Override
    public Page<PublicCodeDto> selectPublicCodePageList(PublicCodeDefaultDto searchDto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;

        Long cnt = jpaQuery.select(qPublicCode.count())
                .from(qPublicCode)
                .fetchFirst();

        List<PublicCode> list = jpaQuery.selectFrom(qPublicCode)
                .offset(searchDto.getPageable().getOffset())
                .limit(searchDto.getPageable().getPageSize()).fetch();

        return new PageImpl<>(list.stream().map(PublicCodeDto::new).toList(),searchDto.getPageable(),cnt);
    }

    @Override
    public List<PublicCodeDto> selectPublicCodeList(PublicCodeDefaultDto searchDto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;
        List<PublicCode> list = jpaQuery.selectFrom(qPublicCode).fetch();

        return list.stream().map(PublicCodeDto::new).toList();
    }

    @Override
    public PublicCodeDto selectPublicCode(PublicCodeDto publicCodeDto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;
        PublicCode publicCode = jpaQuery.selectFrom(qPublicCode)
                .where(new BooleanBuilder().and(qPublicCode.pubCd.eq(publicCodeDto.getPubCd())))
                .fetchFirst();

        if(publicCode == null){
            return null;
        }
        return new PublicCodeDto(publicCode);
    }

    @Override
    public Long selectPublicCodeTotalCount(PublicCodeDefaultDto searchDto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;

        Long cnt = jpaQuery.select(qPublicCode.count())
                .from(qPublicCode)
                .fetchFirst();
        return cnt;
    }

    @Override
    public boolean insertPubliceCode(PublicCodeDto dto) throws Exception {
//        return entityManager.createNativeQuery("insert into cms_file(pub_cd , parent_cd, title, remark, etc1, etc2, etc3, use_yn, ord, create_date, modify_date)" +
//                ") values(?,?,?,?,?,?,?,?,?,?,?)")
        return entityManager.createNativeQuery("insert into cms_public_code(pub_cd, parent_cd, title, remark, etc1, etc2, etc3, use_yn, ord, create_date, modify_date) " +
                        "values(?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1,dto.getPubCd())
                .setParameter(2,dto.getParentCd())
                .setParameter(3,dto.getTitle())
                .setParameter(4,dto.getRemark())
                .setParameter(5,dto.getEtc1())
                .setParameter(6,dto.getEtc2())
                .setParameter(7,dto.getEtc3())
                .setParameter(8,dto.getUseYn())
                .setParameter(9,dto.getOrd())
                .setParameter(10,LocalDateTime.now())
                .setParameter(11,LocalDateTime.now())
                .executeUpdate() > 0;
    }

    @Override
    public boolean updatePublicCode(PublicCodeDto dto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;
        return jpaQuery.update(qPublicCode)
                .set(qPublicCode.title,dto.getTitle())
                .set(qPublicCode.etc1,dto.getEtc1())
                .set(qPublicCode.etc2,dto.getEtc2())
                .set(qPublicCode.etc3,dto.getEtc3())
                .set(qPublicCode.useYn,dto.getUseYn())
                .set(qPublicCode.ord,dto.getOrd())
                .set(qPublicCode.modifyDate, LocalDateTime.now())
                .where(new BooleanBuilder().and(qPublicCode.pubCd.eq(dto.getPubCd()))).execute() > 0;
    }

    @Override
    public boolean deletePublicCode(PublicCodeDto dto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;
        return jpaQuery.delete(qPublicCode)
                .where(new BooleanBuilder().and(qPublicCode.pubCd.eq(dto.getPubCd()))).execute() > 0;
    }

    @Override
    public boolean updatePublicCodeOrd(PublicCodeDto dto) throws Exception {
        QPublicCode qPublicCode = QPublicCode.publicCode;
        return jpaQuery.update(qPublicCode)
                .set(qPublicCode.ord,dto.getOrd())
                .where(new BooleanBuilder().and(qPublicCode.pubCd.eq(dto.getPubCd()))).execute() > 0;
    }
}
