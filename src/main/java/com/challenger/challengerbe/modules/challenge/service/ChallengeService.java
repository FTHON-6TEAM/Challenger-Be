package com.challenger.challengerbe.modules.challenge.service;

import com.challenger.challengerbe.cms.file.service.CmsFileService;
import com.challenger.challengerbe.modules.challenge.domain.Challenge;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeItem;
import com.challenger.challengerbe.modules.challenge.domain.ChallengeUser;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeItemDto;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeSummaryResponse;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeItemRepository;
import com.challenger.challengerbe.modules.challenge.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.service
 * fileName       : ChallengeService
 * author         : rhkdg
 * date           : 2024-09-09
 * description    : 챌린지 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-09        rhkdg       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    private final ChallengeItemRepository challengeItemRepository;

    private final CmsFileService cmsFileService;

    /**
     * 챌린지 목록(페이징 o)
     * @param searchDto
     * @return
     * @throws Exception
     */
    public Page<ChallengeSummaryResponse> selectChallengePageList(ChallengeDefaultDto searchDto) throws Exception {
        return challengeRepository.selectChallengePageList(searchDto);
    }

    /***
     * 챌린지 목록(페이징 x)
     * @param searchDto
     * @return
     * @throws Exception
     */
    public List<ChallengeSummaryResponse> selectChallengeList(ChallengeDefaultDto searchDto) throws Exception {
        return challengeRepository.selectChallengeList(searchDto);
    }

    /**
     * 챌린지 상세 정보
     * @param challengeDto
     * @return
     * @throws Exception
     */
    public ChallengeDto selectChallengeDto(ChallengeDto challengeDto) throws Exception {
        challengeDto = challengeRepository.selectChallengeDto(challengeDto.getIdx());
        List<ChallengeItem> itemList = challengeItemRepository.findByChallenge_Idx(challengeDto.getIdx());
        challengeDto.setChallengeItemList(itemList.stream().map(ChallengeItemDto::new).toList());
        return challengeDto;
    }

    /**
     * 등록
     * @param challengeDto
     * @throws Exception
     */
    @Transactional
    public void insertChallenge(ChallengeDto challengeDto) throws Exception {
        Challenge challenge = new Challenge(challengeDto);
        List<ChallengeItem> itemList = new ArrayList<>();
        for(ChallengeItemDto itemDto : challengeDto.getChallengeItemList()) {
            ChallengeItem item = new ChallengeItem();
            item.addTitle(itemDto.getTitle());
            item.addChallenge(challenge);
            System.out.println(itemDto.getTitle());
            itemList.add(item);
        }
        challenge.addItemList(itemList);
        challenge = challengeRepository.save(challenge);
        challengeDto.setIdx(challenge.getIdx());
        cmsFileService.processFileCreate(challengeDto);
    }

    /**
     * 수정
     * @param challengeDto
     * @throws Exception
     */
    @Transactional
    public void updateChallenge(ChallengeDto challengeDto) throws Exception {
        Challenge challenge = challengeRepository.findById(challengeDto.getIdx()).orElseThrow(() -> new NullPointerException("challenge not exist"));
        //기존 항목 삭제
        if(!challengeDto.getChallengeItemList().isEmpty()){
            challengeItemRepository.deleteByChallenge_Idx(challengeDto.getIdx());
        }

        for(ChallengeItemDto itemDto : challengeDto.getChallengeItemList()) {
            ChallengeItem item = new ChallengeItem();
            item.addTitle(itemDto.getTitle());
            item.addChallenge(challenge);
            challengeItemRepository.save(item);
        }

        challenge.changeChallenge(challengeDto);
        cmsFileService.processFileUpdate(challengeDto);
    }

    /**
     * 삭제
     * @param challengeDto
     * @throws Exception
     */
    @Transactional
    public void deleteChallenge(ChallengeDto challengeDto) throws Exception {



        cmsFileService.proccessFileDelete(challengeDto);
        challengeItemRepository.deleteByChallenge_Idx(challengeDto.getIdx());
        challengeRepository.deleteById(challengeDto.getIdx());
    }

}
