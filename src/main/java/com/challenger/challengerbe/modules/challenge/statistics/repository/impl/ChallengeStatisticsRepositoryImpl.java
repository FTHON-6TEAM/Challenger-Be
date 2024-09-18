package com.challenger.challengerbe.modules.challenge.statistics.repository.impl;

import com.challenger.challengerbe.common.BaseAbstractRepositoryImpl;
import com.challenger.challengerbe.modules.challenge.dto.ChallengeDefaultDto;
import com.challenger.challengerbe.modules.challenge.statistics.dto.ChalendarMonthResponse;
import com.challenger.challengerbe.modules.challenge.statistics.repository.ChallengeStatisticsRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import org.apache.poi.ss.formula.functions.T;
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

        String sql = """
                    SELECT 
                    IFNULL(MAX(CASE WHEN m = 1 THEN av ELSE 0 END),0) AS month1,
                    IFNULL(MAX(CASE WHEN m = 2 THEN av ELSE 0 END),0) AS month2,
                    IFNULL(MAX(CASE WHEN m = 3 THEN av ELSE 0 END),0) AS month3,
                    IFNULL(MAX(CASE WHEN m = 4 THEN av ELSE 0 END),0) AS month4,
                    IFNULL(MAX(CASE WHEN m = 5 THEN av ELSE 0 END),0) AS month5,
                    IFNULL(MAX(CASE WHEN m = 6 THEN av ELSE 0 END),0) AS month6,
                    IFNULL(MAX(CASE WHEN m = 7 THEN av ELSE 0 END),0) AS month7,
                    IFNULL(MAX(CASE WHEN m = 8 THEN av ELSE 0 END),0) AS month8,
                    IFNULL(MAX(CASE WHEN m = 9 THEN av ELSE 0 END),0) AS month9,
                    IFNULL(MAX(CASE WHEN m = 10 THEN av ELSE 0 END),0) AS month10,
                    IFNULL(MAX(CASE WHEN m = 11 THEN av ELSE 0 END),0) AS month11,
                    IFNULL(MAX(CASE WHEN m = 12 THEN av ELSE 0 END),0) AS month12 
                    FROM ( 
                        SELECT m, ROUND(AVG(av)) av, COUNT(*) c 
                        FROM ( 
                            SELECT T.idx, T.challenge_idx, T.cnt, T.dcnt, ROUND(((T.cnt / T.dcnt) * 100)) av , T.m
                            FROM ( 
                                SELECT a.*, b.cnt, DATEDIFF(c.end_date,c.start_date) dcnt, MONTH(c.start_date) m FROM challenge_user a 
                                LEFT JOIN (
                                    SELECT challenge_user_idx,COUNT(*) cnt  
                                      FROM ( 
                                            select challenge_user_idx, complete_date 
                                                FROM challenge_user_item 
                                                WHERE complete_yn = 'Y' 
                                                GROUP BY complete_date,challenge_user_idx   
                                                ORDER BY complete_date asc
                                           )a GROUP BY challenge_user_idx 
                                      ) b ON a.idx = b.challenge_user_idx
                                JOIN 
                                (
                                    SELECT start_date, end_date, idx
                                      FROM challenge 
                                ) c ON a.challenge_idx = c.idx
                                WHERE 1=1
                """;
                StringBuilder stringBuilder = new StringBuilder(sql);
                if(StringUtils.isBlank(searchDto.getIdk())) {
                    stringBuilder.append(" and idk = '").append(searchDto.getIdk()).append("'");
                }
                if(!StringUtils.isBlank(searchDto.getCode())){
                    stringBuilder.append(" and code = '").append(searchDto.getCode()).append("'");
                }
                stringBuilder.append(")T )TT GROUP BY m ORDER BY m )TTT ");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        return jpaResultMapper.uniqueResult(entityManager.createNativeQuery(stringBuilder.toString()), ChalendarMonthResponse.class);
    }

    @Override
    public ChalendarMonthResponse selectChallengeSuccessStatisticsMonthList(ChallengeDefaultDto searchDto) throws Exception {
        String sql = """
                    SELECT 
                    IFNULL(MAX(CASE WHEN m = 1 THEN av ELSE 0 END),0) AS month1,
                    IFNULL(MAX(CASE WHEN m = 2 THEN av ELSE 0 END),0) AS month2,
                    IFNULL(MAX(CASE WHEN m = 3 THEN av ELSE 0 END),0) AS month3,
                    IFNULL(MAX(CASE WHEN m = 4 THEN av ELSE 0 END),0) AS month4,
                    IFNULL(MAX(CASE WHEN m = 5 THEN av ELSE 0 END),0) AS month5,
                    IFNULL(MAX(CASE WHEN m = 6 THEN av ELSE 0 END),0) AS month6,
                    IFNULL(MAX(CASE WHEN m = 7 THEN av ELSE 0 END),0) AS month7,
                    IFNULL(MAX(CASE WHEN m = 8 THEN av ELSE 0 END),0) AS month8,
                    IFNULL(MAX(CASE WHEN m = 9 THEN av ELSE 0 END),0) AS month9,
                    IFNULL(MAX(CASE WHEN m = 10 THEN av ELSE 0 END),0) AS month10,
                    IFNULL(MAX(CASE WHEN m = 11 THEN av ELSE 0 END),0) AS month11,
                    IFNULL(MAX(CASE WHEN m = 12 THEN av ELSE 0 END),0) AS month12 
                    FROM (
                        SELECT m, ROUND(AVG(av)) av 
                            FROM ( 
                                SELECT m , ROUND(((cnt / dcnt) * 100 )) av 
                                FROM (
                                    SELECT m, dcnt, count(*) cnt 
                                    FROM (
                                        SELECT a.*, DATEDIFF(c.end_date,c.start_date) dcnt, MONTH(c.start_date) m 
                                        FROM challenge_user a
                                        LEFT JOIN (
                                            SELECT challenge_user_idx, complete_date, count(*) cnt
                                                FROM challenge_user_item where complete_yn = 'Y'
                                                 GROUP BY challenge_user_idx, complete_date 
                                        ) b ON a.idx = b.challenge_user_idx 
                                    JOIN (
                                        SELECT start_date, end_date, idx , success_cnt
                                            FROM challenge 
                                    ) c ON a.challenge_idx = c.idx 
                                    WHERE 1=1 and b.cnt >= c.success_cnt 
                """;
                StringBuilder stringBuilder = new StringBuilder(sql);
                if(StringUtils.isBlank(searchDto.getIdk())) {
                    stringBuilder.append(" and idk = '").append(searchDto.getIdk()).append("'");
                }
                if(!StringUtils.isBlank(searchDto.getCode())){
                    stringBuilder.append(" and code = '").append(searchDto.getCode()).append("'");
                }
                stringBuilder.append(")T GROUP BY m, dcnt )TT )TTT GROUP BY m ) TTTT ");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        return jpaResultMapper.uniqueResult(entityManager.createNativeQuery(stringBuilder.toString()), ChalendarMonthResponse.class);
    }

    @Override
    public ChalendarMonthResponse selectChallengeFailStatisticsMonthList(ChallengeDefaultDto searchDto) throws Exception {
        String sql = """
                      SELECT 
                            IFNULL(MAX(CASE WHEN m = 1 THEN av ELSE 0 END),0) AS month1,
                            IFNULL(MAX(CASE WHEN m = 2 THEN av ELSE 0 END),0) AS month2,
                            IFNULL(MAX(CASE WHEN m = 3 THEN av ELSE 0 END),0) AS month3,
                            IFNULL(MAX(CASE WHEN m = 4 THEN av ELSE 0 END),0) AS month4,
                            IFNULL(MAX(CASE WHEN m = 5 THEN av ELSE 0 END),0) AS month5,
                            IFNULL(MAX(CASE WHEN m = 6 THEN av ELSE 0 END),0) AS month6,
                            IFNULL(MAX(CASE WHEN m = 7 THEN av ELSE 0 END),0) AS month7,
                            IFNULL(MAX(CASE WHEN m = 8 THEN av ELSE 0 END),0) AS month8,
                            IFNULL(MAX(CASE WHEN m = 9 THEN av ELSE 0 END),0) AS month9,
                            IFNULL(MAX(CASE WHEN m = 10 THEN av ELSE 0 END),0) AS month10,
                            IFNULL(MAX(CASE WHEN m = 11 THEN av ELSE 0 END),0) AS month11,
                            IFNULL(MAX(CASE WHEN m = 12 THEN av ELSE 0 END),0) AS month12 
                        FROM (
                            SELECT m, ROUND(AVG(av)) av 
                                FROM ( 
                                    SELECT m , ROUND(((cnt / dcnt) * 100 )) av 
                                    FROM ( 
                                        SELECT m, dcnt, count(*) cnt 
                                        FROM ( 
                                            SELECT a.*, DATEDIFF(c.end_date,c.start_date) dcnt, MONTH(c.start_date) m 
                                              FROM challenge_user a 
                                              LEFT JOIN (
                                                SELECT challenge_user_idx, complete_date, count(*) cnt 
                                                    FROM challenge_user_item 
                                                    where complete_yn = 'Y'
                                                GROUP BY challenge_user_idx, complete_date 
                                            ) b ON a.idx = b.challenge_user_idx 
                                        JOIN ( 
                                            SELECT start_date, end_date, idx , success_cnt 
                                            FROM challenge 
                                        ) c ON a.challenge_idx = c.idx 
                                    WHERE 1=1 and b.cnt < c.success_cnt 
                """;
                StringBuilder stringBuilder = new StringBuilder(sql);
                if(StringUtils.isBlank(searchDto.getIdk())) {
                    stringBuilder.append(" and idk = '").append(searchDto.getIdk()).append("'");
                }
                if(!StringUtils.isBlank(searchDto.getCode())){
                    stringBuilder.append(" and code = '").append(searchDto.getCode()).append("'");
                }
                stringBuilder.append(")T GROUP BY m, dcnt )TT )TTT GROUP BY m ) TTTT");

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        return jpaResultMapper.uniqueResult(entityManager.createNativeQuery(stringBuilder.toString()), ChalendarMonthResponse.class);
    }
}
