package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository
 * fileName       : ChallengeItemRepository
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 항목 repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
public interface ChallengeItemRepository extends JpaRepository<ChallengeItem,Long> {

    List<ChallengeItem> findByChallenge_Idx(Long idx);


    void deleteByChallenge_Idx(Long idx);

}
