package com.challenger.challengerbe.common;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * packageName    : com.challenger.challengerbe.common
 * fileName       : BaseAbstractRepositoryImpl
 * author         : rhkdg
 * date           : 2024-09-08
 * description    : QueryDSL 모듈 선언 비즈니스 impl
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
public abstract class BaseAbstractRepositoryImpl {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final EntityManager entityManager;

    protected final JPAQueryFactory jpaQuery;

    protected BaseAbstractRepositoryImpl(EntityManager entityManager, JPAQueryFactory jpaQuery) {
        this.entityManager = entityManager;
        this.jpaQuery = jpaQuery;
    }

}
