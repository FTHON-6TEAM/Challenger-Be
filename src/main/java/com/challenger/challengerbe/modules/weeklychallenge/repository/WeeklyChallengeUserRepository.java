package com.challenger.challengerbe.modules.weeklychallenge.repository;

import com.challenger.challengerbe.modules.user.domain.User;
import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallengeUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface WeeklyChallengeUserRepository extends JpaRepository<WeeklyChallengeUser, Long> {

    @Query(value = "SELECT wu.idx FROM weekly_challenge_user wu WHERE wu.user_idk = :userIdk", nativeQuery = true)
    List<Long> findAllByUserIdk(@Param("userIdk") String userIdk);
}
