package com.challenger.challengerbe.modules.challenge.repository.impl;

import com.challenger.challengerbe.cms.file.domain.QCmsFile;
import com.challenger.challengerbe.cms.publiccode.domain.QPublicCode;
import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.challenger.challengerbe.modules.challenge.domain.QChallenge;
import com.challenger.challengerbe.modules.challenge.domain.QChallengeItem;
import com.challenger.challengerbe.modules.challenge.domain.QChallengeUser;
import com.challenger.challengerbe.modules.challenge.domain.QChallengeUserItem;
import com.challenger.challengerbe.modules.challenge.dto.*;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeCtRepository;
import com.challenger.challengerbe.modules.user.domain.QUser;
import com.querydsl.core.BooleanBuilder;
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

        if(!StringUtils.isBlank(searchDto.getActiveStatus())){
            LocalDate today = LocalDate.now();
            DateTemplate formattedDate = Expressions.dateTemplate(String.class,"DATE_FORMAT({0},{1})",qChallenge.createDate,"yyyy-mm-dd");
            DateTemplate formattedDate2 = Expressions.dateTemplate(String.class,"DATE_FORMAT({0},{1})",today,"yyyy-mm-dd");
            if(searchDto.getActiveStatus().equals(ChallengeActiveType.JOIN.name())){
                sql.and(formattedDate.loe(today.toString())).and(qChallenge.startDate.gt(today.toString()));
            }else if(searchDto.getActiveStatus().equals(ChallengeActiveType.KEEP.name())){
                sql.and(formattedDate2.between(qChallenge.startDate,qChallenge.endDate));
            }else {
                sql.and(qChallenge.endDate.lt(today.toString()));
            }
        }else {
            if(!StringUtils.isBlank(searchDto.getStartDate()) && !StringUtils.isBlank(searchDto.getEndDate())){
                sql.and(qChallenge.startDate.goe(searchDto.getStartDate()).and(qChallenge.endDate.loe(searchDto.getEndDate())));
            }
        }

        return sql;
    }

    @Override
    public Page<ChallengeSummaryResponse> selectChallengePageList(ChallengeDefaultDto searchDto) throws Exception {
        QChallenge qChallenge = QChallenge.challenge;
        QChallengeUser qChallengeUser =  QChallengeUser.challengeUser;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QUser qUser = QUser.user;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        long totCnt = jpaQuery.select(qChallenge.count())
                .from(qChallenge).where(commonQuery(searchDto)).fetchFirst();

        List<ChallengeSummaryResponse> list = jpaQuery.select(
                Projections.constructor(
                        ChallengeSummaryResponse.class,
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
                        qCmsFile.idx,
                        ExpressionUtils.as(
                                JPAExpressions.select(qChallengeUser.count())
                                        .from(qChallengeUser)
                                        .where(qChallenge.idx.eq(qChallengeUser.challenge.idx).and(qChallengeUser.user.idk.ne(searchDto.getIdk())))

                        ,"joinCnt"),
                        qChallenge.createDate,
                        qChallenge.modifyDate
                )
        ).from(qChallenge)
                .leftJoin(qPublicCode).on(qChallenge.publicCode.pubCd.eq(qPublicCode.pubCd))
                .leftJoin(qUser).on(qChallenge.user.idk.eq(qUser.idk))
                .leftJoin(qCmsFile)
                .on(qCmsFile.parentIdx.eq(qChallenge.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.challenge.create")))
                .where(commonQuery(searchDto))
                .orderBy(qChallenge.idx.desc())
                .offset(searchDto.getPageable().getOffset())
                .limit(searchDto.getPageable().getPageSize())
                .fetch();

        return new PageImpl<>(list,searchDto.getPageable(),totCnt);
    }

    @Override
    public List<ChallengeSummaryResponse> selectChallengeList(ChallengeDefaultDto searchDto) throws Exception {
        QChallenge qChallenge = QChallenge.challenge;
        QChallengeUser qChallengeUser =  QChallengeUser.challengeUser;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QUser qUser = QUser.user;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        return jpaQuery.select(
                        Projections.constructor(
                                ChallengeSummaryResponse.class,
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
                                qCmsFile.idx,
                                ExpressionUtils.as(
                                        JPAExpressions.select(qChallengeUser.count())
                                                .from(qChallengeUser)
                                                .where(qChallenge.idx.eq(qChallengeUser.challenge.idx).and(qChallengeUser.user.idk.ne(searchDto.getIdk())))

                                        ,"joinCnt"),
                                qChallenge.createDate,
                                qChallenge.modifyDate
                        )
                ).from(qChallenge)
                .leftJoin(qPublicCode).on(qChallenge.publicCode.pubCd.eq(qPublicCode.pubCd))
                .leftJoin(qUser).on(qChallenge.user.idk.eq(qUser.idk))
                .leftJoin(qCmsFile).on(qCmsFile.parentIdx.eq(qChallenge.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.challenge.create")))
                .where(commonQuery(searchDto))
                .orderBy(qChallenge.idx.desc())
                .fetch();
    }

    @Override
    public List<ChallengeItemDto> selectChallengeItemListForUser(ChallengeItemDto itemDto, ChallengeUserItemDto dto) throws Exception {

        QChallengeUserItem qChallengeUserItem = QChallengeUserItem.challengeUserItem;
        QChallengeItem qChallengeItem = QChallengeItem.challengeItem;

        BooleanBuilder sql = new BooleanBuilder().and(qChallengeItem.challenge.idx.eq(itemDto.getChallengeIdx()))
                .and(qChallengeUserItem.challengeUser.idx.eq(dto.getChallengeUserIdx()));

        if(!StringUtils.isBlank(dto.getCompleteDate())) {
            sql.and(qChallengeUserItem.completeDate.eq(dto.getCompleteDate()));
        }

        return jpaQuery.select(
                        Projections.constructor(
                                ChallengeItemDto.class,
                                qChallengeItem.idx,
                                qChallengeItem.challenge.idx,
                                qChallengeItem.title,
                                qChallengeItem.createDate,
                                qChallengeItem.modifyDate,
                                Projections.constructor(
                                        ChallengeUserItemSummaryResponse.class,
                                        qChallengeUserItem.idx,
                                        qChallengeUserItem.completeDate,
                                        qChallengeUserItem.completeYn
                                )
                        )
                ).from(qChallengeItem)
                .innerJoin(qChallengeUserItem).on(qChallengeItem.idx.eq(qChallengeUserItem.challengeItem.idx))
                .where(sql)
                .fetch();

    }

}
