package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeUserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository
 * fileName       : ChallengeUserItemRepository
 * author         : rhkdg
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
public interface ChallengeUserItemRepository extends JpaRepository<ChallengeUserItem , Long> {

    /**
     * 참여자 정보 삭제시 항목도 삭제
     * @param idx
     * @return
     */
    @Modifying
    @Query("delete from ChallengeUserItem i where i.challengeUser.idx =:idx ")
    int deleteChallengeUserItemForUser(@Param("idx") Long idx);
    
}
