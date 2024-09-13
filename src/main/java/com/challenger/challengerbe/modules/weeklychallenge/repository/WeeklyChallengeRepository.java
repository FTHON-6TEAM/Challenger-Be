package com.challenger.challengerbe.modules.weeklychallenge.repository;

import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.repository
 * fileName       : WeeklyChallengeRepository
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */
@Repository
public interface WeeklyChallengeRepository extends JpaRepository<WeeklyChallenge, Long>, WeeklyChallengeRepositoryCustom {
}
