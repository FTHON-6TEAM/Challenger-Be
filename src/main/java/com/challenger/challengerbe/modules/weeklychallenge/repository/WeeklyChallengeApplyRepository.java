package com.challenger.challengerbe.modules.weeklychallenge.repository;

import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallengeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.repository
 * fileName       : WeeklyChallengeApplyRepository
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */
@Repository
public interface WeeklyChallengeApplyRepository extends JpaRepository<WeeklyChallengeUser, Long> {
}
