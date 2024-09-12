package com.challenger.challengerbe.modules.answer.repository;

import com.challenger.challengerbe.modules.answer.domain.Answer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.challenger.challengerbe.modules.answer.repository
 * fileName       : AnswerRepository
 * author         : jongh
 * date           : 2024-09-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-10           jongh          최초 생성
 */

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswerListByQuestionIdx(Long questionIdx);
}
