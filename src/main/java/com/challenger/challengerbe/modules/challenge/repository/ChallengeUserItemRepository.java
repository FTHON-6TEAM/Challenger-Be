package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeUserItem;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
