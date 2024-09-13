package com.challenger.challengerbe.modules.weeklychallenge.repository;

import com.challenger.challengerbe.modules.weeklychallenge.domain.QWeeklyChallenge;
import com.challenger.challengerbe.modules.weeklychallenge.domain.QWeeklyChallengeItem;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeItemDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.repository
 * fileName       : WeeklyChallengeRepositoryCustomImpl
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Component
@RequiredArgsConstructor
public class WeeklyChallengeRepositoryCustomImpl implements WeeklyChallengeRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public WeeklyChallengeDto selectWeeklyChallengeDto(WeeklyChallengeDefaultDto searchDto) {
        QWeeklyChallenge qWeeklyChallenge = QWeeklyChallenge.weeklyChallenge;


        // 현재 날짜
        LocalDate currentDate = LocalDate.now();
        return queryFactory.select(
                Projections.constructor(
                        WeeklyChallengeDto.class,
                        qWeeklyChallenge.idx,
                        qWeeklyChallenge.title,
                        qWeeklyChallenge.createDate,
                        qWeeklyChallenge.modifyDate
                )
                ).from(qWeeklyChallenge)
                .where(qWeeklyChallenge.startDate.loe(currentDate.toString()) // 시작일이 현재 날짜보다 같거나 이전
                        .and(qWeeklyChallenge.endDate.goe(currentDate.toString()))) // 종료일이 현재 날짜보다 같거나 이후
                .fetchOne();
    }

    @Override
    public List<WeeklyChallengeItemDto> selectWeeklyChallengeItemDto(Long weeklyChallengeIdx) {
        QWeeklyChallenge qWeeklyChallenge = QWeeklyChallenge.weeklyChallenge;
        QWeeklyChallengeItem qWeeklyChallengeItem = QWeeklyChallengeItem.weeklyChallengeItem;

        return queryFactory.select(
                        Projections.constructor(
                                WeeklyChallengeItemDto.class,
                                qWeeklyChallengeItem.idx,
                                qWeeklyChallengeItem.weeklyChallenge.idx,
//                        qWeeklyChallenge.idx,
                                qWeeklyChallengeItem.title,
                                qWeeklyChallengeItem.createDate,
                                qWeeklyChallengeItem.modifyDate
                        )
                ).from(qWeeklyChallengeItem)
//                .leftJoin(qWeeklyChallenge)
//                .on(qWeeklyChallengeItem.weeklyChallenge.idx.eq(qWeeklyChallenge.idx))
                .where(qWeeklyChallengeItem.weeklyChallenge.idx.eq(weeklyChallengeIdx))
                .fetch();
    }
}
