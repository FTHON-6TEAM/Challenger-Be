package com.challenger.challengerbe.modules.challenge.repository;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeUserDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeUserItemDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.repository
 * fileName       : ChallengeUserCtRepository
 * author         : rhkdg
 * date           : 2024-09-10
 * description    : 참여자 챌린지 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10        rhkdg       최초 생성
 */
public interface ChallengeUserCtRepository {

    /**
     * 참여자 챌린지 목록 (페이징 o)
     * @param searchDto
     * @return
     * @throws Exception
     */
    Page<ChallengeUserDto> selectChallengeUserPageList(ChallengeDefaultDto searchDto) throws Exception;


    /**
     * 참여자 챌린지 목록(페이징 x)
     * @param searchDto
     * @return
     * @throws Exception
     */
    List<ChallengeUserDto> selectChallengeUserList(ChallengeDefaultDto searchDto) throws Exception;

    /**
     * 참여자 챌린지 상세 정보
     * @param dto
     * @return
     * @throws Exception
     */
    ChallengeUserDto selectChallengeUser(ChallengeUserDto dto) throws Exception;

}
