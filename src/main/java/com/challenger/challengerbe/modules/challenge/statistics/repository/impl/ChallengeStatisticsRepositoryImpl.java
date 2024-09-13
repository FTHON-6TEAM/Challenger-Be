package com.challenger.challengerbe.modules.challenge.statistics.repository.impl;

import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.statistics.dto.ChalendarMonthResponse;
import com.challenger.challengerbe.modules.challenge.statistics.repository.ChallengeStatisticsRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.challenger.challengerbe.modules.challenge.statistics.repository.impl
 * fileName       : ChallengeStatisticsRepositoryImpl
 * author         : rhkdg
 * date           : 2024-09-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-12        rhkdg       최초 생성
 */
@Repository
public class ChallengeStatisticsRepositoryImpl extends BaseAbstractRepositoryImpl implements ChallengeStatisticsRepository {
    protected ChallengeStatisticsRepositoryImpl(EntityManager entityManager, JPAQueryFactory jpaQuery) {
        super(entityManager, jpaQuery);
    }

    @Override
    public ChalendarMonthResponse selectChallengeJoinStatisticsMonthList(ChallengeDefaultDto searchDto) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("MAX(CASE WHEN m = 1 THEN av ELSE 0 END) AS month1,");
        sql.append("MAX(CASE WHEN m = 2 THEN av ELSE 0 END) AS month2,");
        sql.append("MAX(CASE WHEN m = 3 THEN av ELSE 0 END) AS month3,");
        sql.append("MAX(CASE WHEN m = 4 THEN av ELSE 0 END) AS month4,");
        sql.append("MAX(CASE WHEN m = 5 THEN av ELSE 0 END) AS month5,");
        sql.append("MAX(CASE WHEN m = 6 THEN av ELSE 0 END) AS month6,");
        sql.append("MAX(CASE WHEN m = 7 THEN av ELSE 0 END) AS month7,");
        sql.append("MAX(CASE WHEN m = 8 THEN av ELSE 0 END) AS month8,");
        sql.append("MAX(CASE WHEN m = 9 THEN av ELSE 0 END) AS month9,");
        sql.append("MAX(CASE WHEN m = 10 THEN av ELSE 0 END) AS month10,");
        sql.append("MAX(CASE WHEN m = 11 THEN av ELSE 0 END) AS month11,");
        sql.append("MAX(CASE WHEN m = 12 THEN av ELSE 0 END) AS month12 ")
                .append("FROM ( ");
        sql.append("SELECT m, ROUND(AVG(av)) av, COUNT(*) c FROM ( " );
        sql.append("SELECT T.idx, T.challenge_idx, T.cnt, T.dcnt, ROUND(((T.cnt / T.dcnt) * 100)) av , T.m FROM ( ");
        sql.append("SELECT a.*, b.cnt, DATEDIFF(c.end_date,c.start_date) dcnt, MONTH(c.start_date) m FROM challenge_user a ");
        sql.append("LEFT JOIN ( SELECT challenge_user_idx, COUNT(*) cnt FROM challenge_user_item WHERE complete_yn = 'Y' ");
        sql.append("GROUP BY challenge_user_idx ) b ON a.idx = b.challenge_user_idx ");
        sql.append("JOIN ( SELECT start_date, end_date, idx FROM challenge ) c ON a.challenge_idx = c.idx ");
        sql.append("WHERE 1=1 ");
        if(StringUtils.isBlank(searchDto.getIdk())) {
            sql.append("and idk = '").append(searchDto.getIdk()).append("' ");
        }
        if(!StringUtils.isBlank(searchDto.getCode())){
            sql.append("and code = '").append(searchDto.getCode()).append("' ");
        }
        sql.append(")T )TT GROUP BY m ORDER BY m )TTT ");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        return jpaResultMapper.uniqueResult(entityManager.createNativeQuery(sql.toString()), ChalendarMonthResponse.class);
    }
}
