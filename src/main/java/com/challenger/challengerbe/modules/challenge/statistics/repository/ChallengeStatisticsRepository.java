package com.challenger.challengerbe.modules.challenge.statistics.repository;

import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.statistics.dto.ChalendarMonthResponse;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.statistics.repository
 * fileName       : ChallengeStatisticsRepository
 * author         : rhkdg
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12        rhkdg       최초 생성
 */
public interface ChallengeStatisticsRepository {

    /**
     * 챌린지 참여율 월별 조회
     * @param searchDto
     * @return
     * @throws Exception
     */
    ChalendarMonthResponse selectChallengeJoinStatisticsMonthList(ChallengeDefaultDto searchDto) throws Exception;


}
