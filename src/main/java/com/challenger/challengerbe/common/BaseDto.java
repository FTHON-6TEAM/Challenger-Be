package com.challenger.challengerbe.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * packageName    : com.challenger.challengerbe.common
 * fileName       : BaseDto
 * author         : rhkdg
 * date           : 2024-09-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-08        rhkdg       최초 생성
 */
@Getter
@Setter
public class BaseDto {

    /**공통 입력*/
    protected String sstring = "";

    /**공통 타입*/
    protected String stype = "";

    /**페이지 번호*/
    protected int page = 1;

    /**페이지 개수*/
    protected int pageunit = 10;

    protected Sort sort = null;

    public Pageable getPageable() {
        if(sort != null) {
            return PageRequest.of((this.page - 1),this.pageunit,sort);
        }
        return PageRequest.of((this.page - 1),this.pageunit);
    }

}
