package com.challenger.challengerbe.modules.challenge.repository.impl;

import com.challenger.challengerbe.cms.publiccode.domain.QPublicCode;
import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.challenge.domain.QChallenge;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeCtRepository;
import com.challenger.challengerbe.modules.user.domain.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository.impl
 * fileName       : ChallengeCtRepositoryImpl
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 custom repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Repository
public class ChallengeCtRepositoryImpl extends BaseAbstractRepositoryImpl implements ChallengeCtRepository {
    protected ChallengeCtRepositoryImpl(EntityManager entityManager, JPAQueryFactory jpaQuery) {
        super(entityManager, jpaQuery);
    }

    //공통 코드 모듈
    private BooleanBuilder commonQuery(ChallengeDefaultDto searchDto)  {
        QChallenge qChallenge = QChallenge.challenge;
        BooleanBuilder sql = new BooleanBuilder();
        if(!StringUtils.isBlank(searchDto.getCode())) {
            sql.and(qChallenge.publicCode.pubCd.eq(searchDto.getCode()));
        }
        if(!StringUtils.isBlank(searchDto.getStartDate()) && !StringUtils.isBlank(searchDto.getEndDate())){
            sql.and(qChallenge.startDate.goe(searchDto.getStartDate()).and(qChallenge.endDate.loe(searchDto.getEndDate())));
        }
        return sql;
    }

    @Override
    public Page<ChallengeDto> selectChallengePageList(ChallengeDefaultDto searchDto) throws Exception {
        QChallenge qChallenge = QChallenge.challenge;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QUser qUser = QUser.user;

        long totCnt = jpaQuery.select(qChallenge.count())
                .from(qChallenge).where(commonQuery(searchDto)).fetchFirst();

        List<ChallengeDto> list = jpaQuery.select(
                Projections.constructor(
                        ChallengeDto.class,
                        qChallenge.idx,
                        qPublicCode.pubCd,
                        qPublicCode.title,
                        qUser.username,
                        qUser.idk,
                        qChallenge.startDate,
                        qChallenge.endDate,
                        qChallenge.successCnt,
                        qChallenge.title,
                        qChallenge.remark,
                        qChallenge.createDate,
                        qChallenge.modifyDate
                )
        ).from(qChallenge)
                .leftJoin(qPublicCode).on(qChallenge.publicCode.pubCd.eq(qPublicCode.pubCd))
                .leftJoin(qUser).on(qChallenge.user.idk.eq(qUser.idk))
                .where(commonQuery(searchDto))
                .offset(searchDto.getPageable().getOffset())
                .limit(searchDto.getPageable().getPageSize())
                .fetch();

        return new PageImpl<>(list,searchDto.getPageable(),totCnt);
    }

    @Override
    public List<ChallengeDto> selectChallengeList(ChallengeDefaultDto searchDto) throws Exception {
        QChallenge qChallenge = QChallenge.challenge;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QUser qUser = QUser.user;

        return jpaQuery.select(
                        Projections.constructor(
                                ChallengeDto.class,
                                qChallenge.idx,
                                qPublicCode.pubCd,
                                qPublicCode.title,
                                qUser.username,
                                qUser.idk,
                                qChallenge.startDate,
                                qChallenge.endDate,
                                qChallenge.successCnt,
                                qChallenge.title,
                                qChallenge.remark,
                                qChallenge.createDate,
                                qChallenge.modifyDate
                        )
                ).from(qChallenge)
                .leftJoin(qPublicCode).on(qChallenge.publicCode.pubCd.eq(qPublicCode.pubCd))
                .leftJoin(qUser).on(qChallenge.user.idk.eq(qUser.idk))
                .where(commonQuery(searchDto))
                .fetch();
    }
}
