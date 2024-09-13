package com.challenger.challengerbe.modules.question.repository;

import com.challenger.challengerbe.cms.file.domain.QCmsFile;
import com.challenger.challengerbe.cms.publiccode.domain.QPublicCode;
import com.challenger.challengerbe.modules.question.domain.QQuestion;
import com.challenger.challengerbe.modules.question.dto.QuestionListDto;
import com.challenger.challengerbe.modules.question.dto.QuestionSummaryResponse;
import com.challenger.challengerbe.modules.user.domain.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.challenger.challengerbe.modules.question.repository
 * fileName       : QuestionRepositoryImpl
 * author         : jongh
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12           jongh          최초 생성
 */

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<QuestionSummaryResponse> selectQuestionList(QuestionListDto searchDto) {
        QQuestion qQuestion = QQuestion.question;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QUser qUser = QUser.user;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        // 1. 전체 개수 조회
        long totCnt = queryFactory
                .select(qQuestion.count())
                .from(qQuestion)
                .where(commonQuery(searchDto))
                .fetchFirst();

        // 2. 데이터 조회
        List<QuestionSummaryResponse> list = queryFactory.select(
                Projections.constructor(
                        QuestionSummaryResponse.class,
                        qQuestion.idx,
                        qPublicCode.pubCd,
                        qPublicCode.title,
                        qQuestion.title,
                        qQuestion.content,
                        qUser.username,
                        qUser.email,
                        qUser.idk,
                        qCmsFile.idx,
                        qQuestion.createDate,
                        qQuestion.modifyDate
                )
        ).from(qQuestion)
                .leftJoin(qPublicCode).on(qQuestion.publicCode.pubCd.eq(qPublicCode.pubCd))
                .leftJoin(qUser).on(qQuestion.user.idk.eq(qUser.idk))
                .leftJoin(qCmsFile)
                .on(qCmsFile.parentIdx.eq(qQuestion.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.question")))
                .where(commonQuery(searchDto))
                .offset(searchDto.getPageable().getOffset())
                .limit(searchDto.getPageable().getPageSize())
                .fetch();
        // 3. Page 객체 반환
        return new PageImpl<>(list, searchDto.getPageable(), totCnt);
    }

    @Override
    public QuestionSummaryResponse selectQuestionDto(Long questionIdk) {
        QQuestion qQuestion = QQuestion.question;
        QPublicCode qPublicCode = QPublicCode.publicCode;
        QUser qUser = QUser.user;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        return queryFactory.select(
                        Projections.constructor(
                                QuestionSummaryResponse.class,
                                qQuestion.idx,
                                qPublicCode.pubCd,
                                qPublicCode.title,
                                qQuestion.title,
                                qQuestion.content,
                                qUser.username,
                                qUser.email,
                                qUser.idk,
                                qCmsFile.idx,
                                qQuestion.createDate,
                                qQuestion.modifyDate
                        )
                ).from(qQuestion)
                .leftJoin(qPublicCode).on(qQuestion.publicCode.pubCd.eq(qPublicCode.pubCd))
                .leftJoin(qUser).on(qQuestion.user.idk.eq(qUser.idk))
                .leftJoin(qCmsFile)
                .on(qCmsFile.parentIdx.eq(qQuestion.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.question")))
                .where(qQuestion.idx.eq(questionIdk))
                .fetchFirst();
    }

    @Override
    public Long selectFileIdxByQuestion(Long questionIdk) {
        QQuestion qQuestion = QQuestion.question;
        QCmsFile qCmsFile = QCmsFile.cmsFile;

        Long fileIdx = queryFactory.select(
                        Projections.constructor(
                                Long.class,
                                qCmsFile.idx
                        )
                ).from(qQuestion)
                .from(qQuestion)
                .leftJoin(qCmsFile)
                .on(qCmsFile.parentIdx.eq(qQuestion.idx.stringValue())
                        .and(qCmsFile.uploadCode.eq("upload.question")))
                .where(qQuestion.idx.eq(questionIdk))
                .fetchFirst();
        return fileIdx;

    }

    // 공통 쿼리 조건 처리 메소드
    private BooleanBuilder commonQuery(QuestionListDto searchDto) {
        QQuestion qQuestion = QQuestion.question;
        BooleanBuilder sql = new BooleanBuilder();

        // 조건에 따른 필터링
//        if(!io.micrometer.common.util.StringUtils.isBlank(searchDto.getCode())) {
//            sql.and(qQuestion.publicCode.pubCd.eq(searchDto.getCode()));
//        }

        if (!StringUtils.isBlank(searchDto.getCode())) {
            sql.and(qQuestion.title.containsIgnoreCase(searchDto.getCode()));
        }

//        if (!StringUtils.isBlank(searchDto.getStatus())) {
//            sql.and(qQuestion.status.eq(searchDto.getStatus()));
//        }
//
//        if (searchDto.getStartDate() != null && searchDto.getEndDate() != null) {
//            sql.and(qQuestion.createDate.between(searchDto.getStartDate(), searchDto.getEndDate()));
//        }

        return sql;
    }
}
