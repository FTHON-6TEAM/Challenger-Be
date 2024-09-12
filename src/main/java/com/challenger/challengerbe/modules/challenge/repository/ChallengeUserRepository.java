package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository
 * fileName       : ChallengeUserRepository
 * author         : rhkdg
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
public interface ChallengeUserRepository extends JpaRepository<ChallengeUser, Long>,ChallengeUserCtRepository {

    /**
     * 챌린지 방 일련번호와 사용자 키값을 통해 데이터 조회
     * @param idx
     * @param idk
     * @return
     */
    @Query("select c from ChallengeUser c where c.challenge.idx =:idx and c.user.idk =:idk")
    ChallengeUser selectChallengeUserByChallengeIdxAndIdk(@Param("idx") Long idx,@Param("idk") String idk);

    /**
     * 챌린지 방 참여자 수 조회
     * @param idx
     * @return
     */
    @Query(value ="select count(*) from challenge_user where challenge_idx =:idx ",nativeQuery = true)
    int selectChallengeUserTotalCountByChallengeIdx(@Param("idx") Long idx);

}
