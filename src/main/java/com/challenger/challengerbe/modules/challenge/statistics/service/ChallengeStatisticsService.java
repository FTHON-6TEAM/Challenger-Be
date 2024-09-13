package com.challenger.challengerbe.modules.challenge.statistics.service;

import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.statistics.dto.ChalendarMonthResponse;
import com.challenger.challengerbe.modules.challenge.statistics.repository.ChallengeStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.statistics.service
 * fileName       : ChallengeStatisticsService
 * author         : rhkdg
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12        rhkdg       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ChallengeStatisticsService {

    private final ChallengeStatisticsRepository challengeStatisticsRepository;

    /**
     * 월별 참여율 통계 자료
     * @param searchDto
     * @return
     * @throws Exception
     */
    public ChalendarMonthResponse selectChallengeJoinStatisticsMonthList(ChallengeDefaultDto searchDto) throws Exception {
        return challengeStatisticsRepository.selectChallengeJoinStatisticsMonthList(searchDto);
    }
 
}
