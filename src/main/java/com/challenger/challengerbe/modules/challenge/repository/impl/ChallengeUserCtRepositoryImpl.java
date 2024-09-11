package com.challenger.challengerbe.modules.challenge.repository.impl;

import com.challenger.challengerbe.cms.file.domain.QCmsFile;
import com.challenger.challengerbe.cms.publiccode.domain.QPublicCode;
import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import com.challenger.challengerbe.modules.challenge.domain.QChallenge;
import com.challenger.challengerbe.modules.challenge.domain.QChallengeUser;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeActiveType;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeSummaryInfoResponse;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeUserDto;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeUserCtRepository;
import com.challenger.challengerbe.modules.user.domain.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository.impl
 * fileName       : ChallengeUserCtRepositoryImpl
 * author         : rhkdg
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11        rhkdg       최초 생성
 */
@Repository
public class ChallengeUserCtRepositoryImpl extends BaseAbstractRepositoryImpl implements ChallengeUserCtRepository {
    protected ChallengeUserCtRepositoryImpl(EntityManager entityManager, JPAQueryFactory jpaQuery) {
        super(entityManager, jpaQuery);
    }

    //공통 코드 모듈
    private BooleanBuilder commonQuery(ChallengeDefaultDto searchDto)  {
        QChallengeUser qChallengeUser = QChallengeUser.challengeUser;
        QChallenge qChallenge = QChallenge.challenge;
        BooleanBuilder sql = new BooleanBuilder();

        if(!StringUtils.isBlank(searchDto.getIdk())) {
            sql.and(qChallengeUser.user.idk.eq(searchDto.getIdk()));
        }

        if(!StringUtils.isBlank(searchDto.getStartDate()) && !StringUtils.isBlank(searchDto.getEndDate())){
            sql.and(qChallenge.startDate.goe(searchDto.getStartDate()).and(qChallenge.endDate.loe(searchDto.getEndDate())));
        }

        return sql;
    }

    @Override
    public Page<ChallengeUserDto> selectChallengeUserPageList(ChallengeDefaultDto searchDto) throws Exception {
        QChallengeUser qChallengeUser = QChallengeUser.challengeUser;
        QChallengeUser qChallengeUser2 = QChallengeUser.challengeUser;
        QChallenge qChallenge = QChallenge.challenge;
        QUser qUser = QUser.user;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        long totCnt = jpaQuery.select(qChallengeUser.count())
                .from(qChallengeUser)
                .innerJoin(qChallenge).on(qChallengeUser.challenge.idx.eq(qChallenge.idx))
                .innerJoin(qUser).on(qChallengeUser.user.idk.eq(qUser.idk))
                .where(commonQuery(searchDto)).fetchFirst();

        List<ChallengeUserDto> list = jpaQuery.select(
                Projections.constructor(
                        ChallengeUserDto.class,
                        qChallengeUser.idx,
                        qUser.idk,
                        qChallengeUser.challenge.idx,
                        qChallengeUser.createDate,
                        qChallengeUser.modifyDate,
                        Projections.constructor(
                                ChallengeSummaryInfoResponse.class,
                                qChallenge.idx,
                                qChallenge.startDate,
                                qChallenge.endDate,
                                qChallenge.successCnt,
                                qChallenge.title,
                                qChallenge.remark,
                                qCmsFile.idx,
                                ExpressionUtils.as(
                                        JPAExpressions.select(qChallengeUser.count())
                                                .from(qChallengeUser)
                                                .where(qChallenge.idx.eq(qChallengeUser.challenge.idx).and(qChallengeUser.user.idk.ne(searchDto.getIdk())))

                                        ,"joinCnt"),
                                qChallenge.createDate,
                                qChallenge.modifyDate
                        )
                )
        ).from(qChallengeUser)
                .innerJoin(qChallenge).on(qChallengeUser.challenge.idx.eq(qChallenge.idx))
                .innerJoin(qUser).on(qChallengeUser.user.idk.eq(qUser.idk))
                .leftJoin(qCmsFile)
                .on(qCmsFile.parentIdx.eq(qChallenge.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.challenge.create")))
                .where(commonQuery(searchDto))
                .offset(searchDto.getPageable().getOffset())
                .limit(searchDto.getPageable().getPageSize())
                .fetch();

        return new PageImpl<>(list,searchDto.getPageable(),totCnt);
    }

    @Override
    public List<ChallengeUserDto> selectChallengeUserList(ChallengeDefaultDto searchDto) throws Exception {
        QChallengeUser qChallengeUser = QChallengeUser.challengeUser;
        QChallengeUser qChallengeUser2 = QChallengeUser.challengeUser;
        QChallenge qChallenge = QChallenge.challenge;
        QUser qUser = QUser.user;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        return jpaQuery.select(
                        Projections.constructor(
                                ChallengeUserDto.class,
                                qChallengeUser.idx,
                                qUser.idk,
                                qChallengeUser.challenge.idx,
                                qChallengeUser.createDate,
                                qChallengeUser.modifyDate,
                                Projections.constructor(
                                        ChallengeSummaryInfoResponse.class,
                                        qChallenge.idx,
                                        qChallenge.startDate,
                                        qChallenge.endDate,
                                        qChallenge.successCnt,
                                        qChallenge.title,
                                        qChallenge.remark,
                                        qCmsFile.idx,
                                        ExpressionUtils.as(
                                                JPAExpressions.select(qChallengeUser.count())
                                                        .from(qChallengeUser)
                                                        .where(qChallenge.idx.eq(qChallengeUser.challenge.idx).and(qChallengeUser.user.idk.ne(searchDto.getIdk())))

                                                ,"joinCnt"),
                                        qChallenge.createDate,
                                        qChallenge.modifyDate
                                )
                        )
                ).from(qChallengeUser)
                .innerJoin(qChallenge).on(qChallengeUser.challenge.idx.eq(qChallenge.idx))
                .innerJoin(qUser).on(qChallengeUser.user.idk.eq(qUser.idk))
                .leftJoin(qCmsFile)
                .on(qCmsFile.parentIdx.eq(qChallenge.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.challenge.create")))
                .where(commonQuery(searchDto))
                .fetch();
    }

    @Override
    public ChallengeUserDto selectChallengeUser(ChallengeUserDto dto) throws Exception {
        QChallengeUser qChallengeUser = QChallengeUser.challengeUser;
        QChallenge qChallenge = QChallenge.challenge;
        QUser qUser = QUser.user;
        return jpaQuery.select(
                        Projections.constructor(
                                ChallengeUserDto.class,
                                qChallengeUser.idx,
                                qUser.idk,
                                qChallengeUser.challenge.idx,
                                qChallengeUser.createDate,
                                qChallengeUser.modifyDate,
                                Projections.constructor(
                                        ChallengeSummaryInfoResponse.class,
                                        qChallenge.idx,
                                        qChallenge.startDate,
                                        qChallenge.endDate,
                                        qChallenge.successCnt,
                                        qChallenge.title,
                                        qChallenge.remark,
                                        Expressions.numberTemplate(Long.class,"0") ,
                                        ExpressionUtils.as(
                                                JPAExpressions.select(qChallengeUser.count())
                                                        .from(qChallengeUser)
                                                        .where(qChallenge.idx.eq(qChallengeUser.challenge.idx).and(qChallengeUser.user.idk.ne(dto.getIdk())))
                                                ,"joinCnt"),
                                        qChallenge.createDate,
                                        qChallenge.modifyDate
                                )
                        )
                ).from(qChallengeUser)
                .innerJoin(qChallenge).on(qChallengeUser.challenge.idx.eq(qChallenge.idx))
                .innerJoin(qUser).on(qChallengeUser.user.idk.eq(qUser.idk))
                .where(new BooleanBuilder().and(qChallengeUser.idx.eq(dto.getIdx())))
                .fetchFirst();
    }
}
