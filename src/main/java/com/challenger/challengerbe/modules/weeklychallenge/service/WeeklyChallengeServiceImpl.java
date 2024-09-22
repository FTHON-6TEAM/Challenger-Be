package com.challenger.challengerbe.modules.weeklychallenge.service;

import com.challenger.challengerbe.cms.publiccode.domain.PublicCode;
import com.challenger.challengerbe.cms.publiccode.dto.PublicCodeDto;
import com.challenger.challengerbe.cms.publiccode.repository.PublicCodeRepository;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeItemDto;
import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallenge;
import com.challenger.challengerbe.modules.weeklychallenge.domain.WeeklyChallengeItem;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDefaultDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeItemDto;
import com.challenger.challengerbe.modules.weeklychallenge.dto.WeeklyChallengeSummaryResponse;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeItemRepository;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeRepository;
import com.challenger.challengerbe.modules.weeklychallenge.repository.WeeklyChallengeUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.challenger.challengerbe.modules.weeklychallenge.service
 * fileName       : WeeklyChallengeServiceImpl
 * author         : jongh
 * date           : 2024-09-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-13           jongh          최초 생성
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeeklyChallengeServiceImpl implements WeeklyChallengeService {

    private final WeeklyChallengeRepository weeklyChallengeRepository;
    private final WeeklyChallengeItemRepository weeklyChallengeItemRepository;
    private final WeeklyChallengeUserRepository weeklyChallengeUserRepository;

    @Override
    public WeeklyChallengeDto selectWeeklyChallengeDto() {
        WeeklyChallengeDto dto = weeklyChallengeRepository.selectWeeklyChallengeDto();
        List<WeeklyChallengeItem> list = weeklyChallengeItemRepository.findWeeklyChallengeItemsByWeeklyChallengeId(dto.getWeeklyChallengeIdx());
        dto.setWeeklyChallengeItemList(list.stream().map(WeeklyChallengeItemDto::new).toList());
        return dto;
    }


    @Override
    public List<WeeklyChallengeDto> selectMyWeeklyChallenge(String userIdk) {
        List<WeeklyChallengeDto> list = weeklyChallengeRepository.selectWeeklyChallengeInIdx(userIdk);
        return list;
    }

    @Override
    @Transactional
    public void insertWeeklyChallenge(WeeklyChallengeDto challengeDto) {
        WeeklyChallenge weeklyChallenge = new WeeklyChallenge(challengeDto.getTitle());

        // 2. WeeklyChallengeItem 리스트 생성 및 연관 관계 설정
        List<WeeklyChallengeItem> list = challengeDto.getWeeklyChallengeItemList().stream()
                .map(itemDto -> {
                    WeeklyChallengeItem item = new WeeklyChallengeItem(itemDto);
                    item.addChallenge(weeklyChallenge);  // 연관 관계 설정
                    return item;
                }).toList();

        // 3. WeeklyChallenge에 WeeklyChallengeItem 리스트 추가
        weeklyChallenge.addItemList(list);
        weeklyChallengeRepository.save(weeklyChallenge);
    }

}
