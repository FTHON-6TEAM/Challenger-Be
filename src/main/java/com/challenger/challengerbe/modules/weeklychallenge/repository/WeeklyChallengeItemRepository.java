package com.challenger.challengerbe.modules.weeklychallenge.repository;

import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallengeItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.repository
 * fileName       : WeeklyChallengeItemRepository
 * author         : jongh
 * date           : 2024-09-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-14           jongh          최초 생성
 */
public interface WeeklyChallengeItemRepository extends JpaRepository<WeeklyChallengeItem, Long> {

    @Query(value = "select * from weekly_challenge_item w where w.weekly_challenge_idx = :weeklyId ", nativeQuery = true)
    List<WeeklyChallengeItem> findWeeklyChallengeItemsByWeeklyChallengeId(@Param("weeklyId") Long weeklyId);
}
