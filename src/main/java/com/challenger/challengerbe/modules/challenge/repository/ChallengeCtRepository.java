package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.challenge.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository
 * fileName       : ChallengeCtRepository
 * author         : rhkdg
 * date           : 2024-09-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
public interface ChallengeCtRepository {

    /**
     * 목록 (페이징 포함)
     * @param searchDto
     * @return
     * @throws Exception
     */
    Page<ChallengeSummaryResponse> selectChallengePageList(ChallengeDefaultDto searchDto) throws Exception;

    /**
     * 목록(페이징 미포함)
     * @param searchDto
     * @return
     * @throws Exception
     */
    List<ChallengeSummaryResponse> selectChallengeList(ChallengeDefaultDto searchDto) throws Exception;


    /**
     * 참여자 챌린지 항목 목록 조회
     * @param dto
     * @return
     * @throws Exception
     */
    List<ChallengeItemDto> selectChallengeItemListForUser(ChallengeItemDto itemDto, ChallengeUserItemDto dto) throws Exception;


}
