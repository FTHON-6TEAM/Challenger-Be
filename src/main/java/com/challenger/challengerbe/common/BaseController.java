package com.challenger.challengerbe.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
* @packageName   : com.routdoo.dailyroutine.common.web
* @fileName      : BaseController.java
* @author        : Gwang hyeok Go
* @date          : 2024.09.08
* @description   : Base 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.09.08      ghgo       최초 생성
 */
public class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final String MGN_URL = "/mgn";

}
