package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeWithItemListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

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

    @Query(value = "select new com.challenger.challengerbe.modules.challenge.dto.ChallengeWithItemListResponse(" +
            "c.idx,c.publicCode.pubCd,c.startDate,c.endDate,c.successCnt,c.title,c.remark,p.title,u.username,c.createDate,c.modifyDate)  from Challenge c left join PublicCode p on c.publicCode.pubCd = p.pubCd " +
            "left join User u on c.user.idk = u.idk where c.idx=:idx")
    ChallengeWithItemListResponse selectChallengeDto(Long idx);

}
