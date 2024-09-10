package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository
 * fileName       : ChallengeRepository
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
public interface ChallengeRepository extends JpaRepository<Challenge,Long> ,ChallengeCtRepository{

    @Query(value = "select a.*, b.title,c.username from challenge a left join cms_public_code b on a.code = b.pub_cd " +
            "left join user c on a.idk = c.idk where idx=:idx",nativeQuery = true)
    ChallengeDto selectChallengeDto(Long idx);

}
