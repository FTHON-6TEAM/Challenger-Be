package com.challenger.challengerbe.modules.challenge.statistics.repository.impl;

import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
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
    public Map<String, String> selectChallengeJoinStatisticsMonthList(ChallengeDefaultDto searchDto) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("MAX(CASE WHEN m = 1 THEN `avg` ELSE 0 END) AS '1월',");
        sql.append("MAX(CASE WHEN m = 2 THEN `avg` ELSE 0 END) AS '2월',");
        sql.append("MAX(CASE WHEN m = 3 THEN `avg` ELSE 0 END) AS '3월',");
        sql.append("MAX(CASE WHEN m = 4 THEN `avg` ELSE 0 END) AS '4월',");
        sql.append("MAX(CASE WHEN m = 5 THEN `avg` ELSE 0 END) AS '5월',");
        sql.append("MAX(CASE WHEN m = 6 THEN `avg` ELSE 0 END) AS '6월',");
        sql.append("MAX(CASE WHEN m = 7 THEN `avg` ELSE 0 END) AS '7월',");
        sql.append("MAX(CASE WHEN m = 8 THEN `avg` ELSE 0 END) AS '8월',");
        sql.append("MAX(CASE WHEN m = 9 THEN `avg` ELSE 0 END) AS '9월',");
        sql.append("MAX(CASE WHEN m = 10 THEN `avg` ELSE 0 END) AS '10월',");
        sql.append("MAX(CASE WHEN m = 11 THEN `avg` ELSE 0 END) AS '11월',");
        sql.append("MAX(CASE WHEN m = 12 THEN `avg` ELSE 0 END) AS '12월' ")
                .append("FROM ( ");
        sql.append("SELECT m, ROUND(AVG(`avg`)) `avg`, COUNT(*) c FROM ( " );
        sql.append("SELECT T.idx, T.challenge_idx, T.cnt, T.dcnt, ROUND(((T.cnt / T.dcnt) * 100)) `avg`, T.m FROM ( ");
        sql.append("SELECT a.*, b.cnt, DATEDIFF(c.end_date,c.start_date) dcnt, MONTH(c.start_date) m FROM challenge_user a ");
        sql.append("LEFT JOIN ( SELECT challenge_user_idx, COUNT(*) cnt FROM challenge_user_item WHERE complete_yn = 'Y' ");
        sql.append("GROUP BY challenge_user_idx ) b ON a.idx = b.challenge_user_idx ");
        sql.append("JOIN ( SELECT start_date, end_date, idx FROM challenge ) c ON a.challenge_idx = c.idx ");
        sql.append("WHERE 1=1 ");
        if(StringUtils.isBlank(searchDto.getIdk())) {
            sql.append("and idx = '").append(searchDto.getIdk()).append("' ");
        }
        if(!StringUtils.isBlank(searchDto.getCode())){
            sql.append("and code = '").append(searchDto.getCode()).append("' ");
        }
        sql.append(")T )TT GROUP BY m ORDER BY m )TTT ");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Map<?,?> resultMap = jpaResultMapper.uniqueResult(entityManager.createNativeQuery(sql.toString()), Map.class);

        return (Map<String, String>) resultMap;
    }
}
