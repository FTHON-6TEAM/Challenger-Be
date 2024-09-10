package com.challenger.challengerbe.modules.challenge.service;

import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeUserItem;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeUserDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeUserItemDto;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeUserCtRepository;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeUserItemRepository;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.service
 * fileName       : ChallengeUserService
 * author         : rhkdg
 * date           : 2024-09-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-11        rhkdg       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeApplyService {

    private final ChallengeUserRepository challengeUserRepository;

    private final ChallengeUserItemRepository challengeUserItemRepository;

    /**
     * 참여중인 챌린지 목록(페이징 o)
     * @param searchDto
     * @return
     * @throws Exception
     */
    public Page<ChallengeUserDto> selectChallengeUserPageList(ChallengeDefaultDto searchDto) throws Exception {
        return challengeUserRepository.selectChallengeUserPageList(searchDto);
    }

    /**
     * 참여중 챌린지 목록(페이징 x)
     * @param searchDto
     * @return
     * @throws Exception
     */
    public List<ChallengeUserDto> selectChallengeUserList(ChallengeDefaultDto searchDto) throws Exception {
        return challengeUserRepository.selectChallengeUserList(searchDto);
    }

    /**
     * 참여중 챌린지 상세 조회
     * @param dto
     * @return
     * @throws Exception
     */
    public ChallengeUserDto selectChallengeUser(ChallengeUserDto dto) throws Exception {
        return challengeUserRepository.selectChallengeUser(dto);
    }

    /**
     * 등록
     * @param dto
     * @throws Exception
     */
    @Transactional
    public void insertChallengeApply(ChallengeUserDto dto) throws Exception {
        ChallengeUser challengeUser = new ChallengeUser(dto);
        challengeUserRepository.save(challengeUser);
    }

    /**
     * 수정
     * @param dto
     * @throws Exception
     */
    @Transactional
    public void insertChallengeApplyItem(ChallengeUserItemDto dto) throws Exception {
        ChallengeUserItem challengeUserItem = new ChallengeUserItem(dto);
        challengeUserItemRepository.save(challengeUserItem);
    }

    /**
     * 삭제
     * @param dto
     * @throws Exception
     */
    @Transactional
    public void deleteChallengeApplyItem(ChallengeUserItemDto dto) throws Exception{
        challengeUserItemRepository.deleteById(dto.getIdx());
    }

}
